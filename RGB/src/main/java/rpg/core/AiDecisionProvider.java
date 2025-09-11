package rpg.core;

import rpg.battle.Battle;
import rpg.skills.Action;
import rpg.skills.BasicAttack;
import rpg.units.Unit;
import java.util.List;
import java.util.ArrayList;


public class AiDecisionProvider implements DecisionProvider {
    private int seed = 0;

    @Override
    public Action chooseAction(Unit unit, Battle battle) {
        List<Action> availableActions = new ArrayList<>();

        availableActions.add(new BasicAttack());

        availableActions.addAll(unit.getSkills());

        while (true) {
            int actionIndex = seed % availableActions.size();
            Action chosen = availableActions.get(actionIndex);

            if (unit.hasEnoughMp(chosen.mpCost())) {
                seed++;
                return chosen;
            } else {
                System.out.println("你缺乏 MP，不能進行此行動。");
                System.out.print("選擇行動：");
                for (int i = 0; i < availableActions.size(); i++) {
                    System.out.print("(" + i + ") " + availableActions.get(i).name());
                    if (i < availableActions.size() - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                seed++;
            }
        }
    }

    @Override
    public List<Unit> chooseTargets(Unit unit, List<Unit> candidates, int needed) {
        List<Unit> selected = new ArrayList<>();

        if (candidates.isEmpty() || needed <= 0) {
            return selected;
        }

        for (int i = 0; i < needed; i++) {
            int targetIndex = (seed + i) % candidates.size();
            Unit target = candidates.get(targetIndex);
            if (!selected.contains(target)) {
                selected.add(target);
            }
        }

        seed++;
        return selected;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
