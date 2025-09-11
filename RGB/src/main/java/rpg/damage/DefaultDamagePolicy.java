package rpg.damage;

import rpg.battle.Battle;
import rpg.states.CheerupState;
import rpg.units.Unit;

public class DefaultDamagePolicy implements DamagePolicy {

    @Override
    public int compute(int baseDamage, Unit source, Unit target, Battle battle) {
        int actualDamage = baseDamage;

        if (source.getState() instanceof CheerupState) {
            actualDamage += 50;
        }

        actualDamage = target.getState().onReceiveDamage(target, actualDamage, source, battle);

        return Math.max(0, actualDamage);
    }
}

