package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;


public class NormalState implements State {

    @Override
    public void onTurnStart(Unit unit, Battle battle) {
    }

    @Override
    public int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle) {
        return amount; // 不修改傷害
    }

    @Override
    public int ticksRemaining() {
        return 0;
    }

    @Override
    public void tickDown() {
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



