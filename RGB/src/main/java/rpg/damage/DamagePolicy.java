package rpg.damage;

import rpg.battle.Battle;
import rpg.units.Unit;

public interface DamagePolicy {

    int compute(int baseDamage, Unit source, Unit target, Battle battle);
}



