package rpg.onepunch;

import rpg.battle.Battle;
import rpg.states.PetrochemicalState;
import rpg.states.PoisonedState;
import rpg.units.Unit;


public class BadStateRule implements OnePunchRule {

    @Override
    public boolean applies(Unit target, Battle battle) {
        return target.getState() instanceof PoisonedState ||
                target.getState() instanceof PetrochemicalState;
    }

    @Override
    public void apply(Unit caster, Unit target, Battle battle) {
        for (int i = 0; i < 3; i++) {
            if (target.isDead()) {
                break;
            }

            int damage = battle.getDamagePolicy().compute(80, caster, target, battle);
            target.takeDamage(damage);

            System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

            if (target.isDead()) {
                System.out.println(target.getDisplayName() + " 死亡。");
                battle.getDeathBus().notifyDeath(target, caster, battle);
                break;
            }
        }
    }
}

