package rpg.death;

import rpg.battle.Battle;
import rpg.units.Unit;

public interface DeathListener {

    void onDeath(Unit dead, Unit killer, Battle battle);
}



