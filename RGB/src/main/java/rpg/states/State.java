package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;


public interface State {

    void onTurnStart(Unit unit, Battle battle);

    int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle);

    int ticksRemaining();

    void tickDown();

    String getName();

    boolean canAct();
}
