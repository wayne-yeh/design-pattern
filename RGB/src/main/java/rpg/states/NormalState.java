package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 正常狀態
 */
public class NormalState implements State {

    @Override
    public void onTurnStart(Unit unit, Battle battle) {
        // 正常狀態下無特殊效果
    }

    @Override
    public int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle) {
        return amount; // 不修改傷害
    }

    @Override
    public int ticksRemaining() {
        return 0; // 正常狀態永不結束
    }

    @Override
    public void tickDown() {
        // 正常狀態不需要倒數
    }

    @Override
    public String getName() {
        return "正常";
    }

    @Override
    public boolean canAct() {
        return true;
    }
}
