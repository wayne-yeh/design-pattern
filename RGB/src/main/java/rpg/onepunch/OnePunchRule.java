package rpg.onepunch;

import rpg.battle.Battle;
import rpg.units.Unit;


public interface OnePunchRule {

    boolean applies(Unit target, Battle battle);

    void apply(Unit caster, Unit target, Battle battle);
}

