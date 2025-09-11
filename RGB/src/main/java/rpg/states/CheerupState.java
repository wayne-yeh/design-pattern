package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 受到鼓舞狀態
 */
public class CheerupState implements State {
    private int ticksRemaining;

    public CheerupState() {
        this.ticksRemaining = 3; // 三回合（含當前回合）
    }

    @Override
    public void onTurnStart(Unit unit, Battle battle) {
        // 受到鼓舞狀態下無特殊開始效果
    }

    @Override
    public int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle) {
        return amount; // 不修改受到的傷害
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
        return "受到鼓舞";
    }

    @Override
    public boolean canAct() {
        return true;
    }
}



