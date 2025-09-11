package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 中毒狀態
 */
public class PoisonedState implements State {
    private int ticksRemaining;

    public PoisonedState() {
        this.ticksRemaining = 3; // 三回合（含當前回合）
    }

    @Override
    public void onTurnStart(Unit unit, Battle battle) {
        // 輪到此角色時，HP 減少 30
        unit.takeDamage(30);
        if (unit.isDead()) {
            System.out.println(unit.getDisplayName() + " 因中毒而死亡。");
        }
    }

    @Override
    public int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle) {
        return amount; // 不修改傷害
    }

    @Override
    public int ticksRemaining() {
        return ticksRemaining;
    }

    @Override
    public void tickDown() {
        if (ticksRemaining > 0) {
            ticksRemaining--;
        }
    }

    @Override
    public String getName() {
        return "中毒";
    }

    @Override
    public boolean canAct() {
        return true; // 中毒狀態下仍可行動（如果沒死）
    }
}



