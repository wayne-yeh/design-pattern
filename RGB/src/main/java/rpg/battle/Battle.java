package rpg.battle;

import rpg.core.DecisionProvider;
import rpg.damage.DamagePolicy;
import rpg.damage.DefaultDamagePolicy;
import rpg.death.DeathBus;
import rpg.death.CurseTracker;
import rpg.death.SummonHealRule;
import rpg.skills.Action;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Battle {
    private Troop troop1;
    private Troop troop2;
    private Queue<Unit> turnQueue;
    private DamagePolicy damagePolicy;
    private DeathBus deathBus;
    private CurseTracker curseTracker;
    private SummonHealRule summonHealRule;
    private boolean gameEnded;
    private boolean playerWon;

    public Battle(Troop troop1, Troop troop2) {
        this.troop1 = troop1;
        this.troop2 = troop2;
        this.turnQueue = new LinkedList<>();
        this.damagePolicy = new DefaultDamagePolicy();
        this.deathBus = new DeathBus();
        this.curseTracker = new CurseTracker();
        this.summonHealRule = new SummonHealRule();
        this.gameEnded = false;
        this.playerWon = false;

        this.deathBus.subscribe(curseTracker);
        this.deathBus.subscribe(summonHealRule);

        initializeTurnQueue();
    }

    private void initializeTurnQueue() {
        for (Unit unit : troop1.getUnits()) {
            if (unit.isAlive()) {
                turnQueue.offer(unit);
            }
        }
        for (Unit unit : troop2.getUnits()) {
            if (unit.isAlive()) {
                turnQueue.offer(unit);
            }
        }
    }

    public void start() {
        System.out.println("戰鬥開始！");

        while (!gameEnded) {
            step();
        }

        if (playerWon) {
            System.out.println("你獲勝了！");
        } else {
            System.out.println("你失敗了！");
        }
    }

    public void step() {
        if (gameEnded || turnQueue.isEmpty()) {
            return;
        }

        Unit currentUnit = turnQueue.poll();

        if (currentUnit.isDead()) {
            initializeTurnQueue();
            return;
        }

        System.out.println("輪到 " + currentUnit + "。");

        currentUnit.getState().onTurnStart(currentUnit, this);

        if (currentUnit.isDead()) {
            deathBus.notifyDeath(currentUnit, null, this);
            checkGameEnd();
            initializeTurnQueue();
            return;
        }

        if (!currentUnit.canAct()) {
            initializeTurnQueue();
            return;
        }

        // S1: 選擇行動
        Action action = chooseAction(currentUnit);
        if (action == null) {
            initializeTurnQueue();
            return;
        }

        // S2: 選擇目標
        List<Unit> targets = chooseTargets(currentUnit, action);

        // S3: 執行行動
        currentUnit.consumeMp(action.mpCost());
        action.execute(currentUnit, targets, this);

        checkGameEnd();

        updateStates();

        if (!gameEnded) {
            initializeTurnQueue();
        }
    }

    private Action chooseAction(Unit unit) {
        List<Action> availableActions = new ArrayList<>();

        // 添加普通攻擊 (始終可用，MP消耗為0)
        availableActions.add(new rpg.skills.BasicAttack());

        // 添加技能
        for (Action skill : unit.getSkills()) {
            availableActions.add(skill);
        }

        // 顯示選項
        System.out.print("選擇行動：");
        for (int i = 0; i < availableActions.size(); i++) {
            System.out.print("(" + i + ") " + availableActions.get(i).name());
            if (i < availableActions.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        Action chosen = unit.getDecisionProvider().chooseAction(unit, this);
        return chosen;
    }

    private List<Unit> chooseTargets(Unit caster, Action action) {
        TargetingPolicy policy = action.targetingPolicy();
        List<Unit> candidates = policy.candidates(caster, this);

        if (candidates.isEmpty()) {
            return new ArrayList<>();
        }

        // 根據目標策略決定需要選擇的目標數量
        int needed = getTargetCount(action, candidates);

        if (needed <= 0 || candidates.size() <= needed) {
            // 自動選擇所有候選目標
            return candidates;
        }

        // 需要手動選擇目標
        if (caster.getName().equals("Hero") || caster.getName().equals("英雄")) {
            System.out.print("選擇 " + needed + " 位目標: ");
            for (int i = 0; i < candidates.size(); i++) {
                System.out.print("(" + i + ") " + candidates.get(i).getDisplayName());
                if (i < candidates.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        return policy.select(caster, candidates, needed, caster.getDecisionProvider());
    }

    private int getTargetCount(Action action, List<Unit> candidates) {
        String actionName = action.name();

        switch (actionName) {
            case "普通攻擊":
            case "水球":
            case "石化":
            case "下毒":
            case "詛咒":
            case "一拳攻擊":
                return 1;
            case "鼓舞":
                return Math.min(3, candidates.size());
            case "火球":
            case "自爆":
                return candidates.size(); // 所有目標
            case "自我治療":
            case "召喚":
                return 0; // 無需選擇
            default:
                return 1;
        }
    }

    private void checkGameEnd() {
        Unit hero = troop1.getHero();

        if (hero != null && hero.isDead()) {
            gameEnded = true;
            playerWon = false;
            return;
        }

        if (troop1.isAnnihilated()) {
            gameEnded = true;
            playerWon = false;
            return;
        }

        if (troop2.isAnnihilated()) {
            gameEnded = true;
            playerWon = true;
            return;
        }
    }

    // 更新狀態倒數
    private void updateStates() {
        for (Unit unit : getAllUnits()) {
            if (unit.isAlive()) {
                unit.tickState();
            }
        }
    }

    // Getter methods
    public Troop getTroop1() {
        return troop1;
    }

    public Troop getTroop2() {
        return troop2;
    }

    public DamagePolicy getDamagePolicy() {
        return damagePolicy;
    }

    public DeathBus getDeathBus() {
        return deathBus;
    }

    public CurseTracker getCurseTracker() {
        return curseTracker;
    }

    public SummonHealRule getSummonHealRule() {
        return summonHealRule;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public List<Unit> getAllUnits() {
        List<Unit> allUnits = new ArrayList<>();
        allUnits.addAll(troop1.getUnits());
        allUnits.addAll(troop2.getUnits());
        return allUnits;
    }

    public List<Unit> getEnemiesOf(Unit unit) {
        if (unit.getTroopId() == 1) {
            return troop2.getAliveUnits();
        } else {
            return troop1.getAliveUnits();
        }
    }

    public List<Unit> getAlliesOf(Unit unit) {
        if (unit.getTroopId() == 1) {
            return troop1.getAliveUnits();
        } else {
            return troop2.getAliveUnits();
        }
    }
}
