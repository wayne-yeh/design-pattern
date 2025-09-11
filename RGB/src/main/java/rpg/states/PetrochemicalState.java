package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 石化狀態
 */
public class PetrochemicalState implements State {
    private int ticksRemaining;

    public PetrochemicalState() {
        this.ticksRemaining = 3; // 三回合（含當前回合）
    }

    @Override
    public void onTurnStart(Unit unit, Battle battle) {
        // 石化狀態下無法行動，但不需要特殊處理
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
        return "石化";
    }

    @Override
    public boolean canAct() {
        return false; // 石化狀態下無法行動
    }
}



