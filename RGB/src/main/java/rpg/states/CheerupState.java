package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;


public class CheerupState implements State {
    private int ticksRemaining;

    public CheerupState() {
        this.ticksRemaining = 3;
    }

    @Override
    public void onTurnStart(Unit unit, Battle battle) {
    }

    @Override
    public int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle) {
        return amount;
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



