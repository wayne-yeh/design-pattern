package rpg.onepunch;

import rpg.battle.Battle;
import rpg.states.NormalState;
import rpg.units.Unit;


public class NormalRule implements OnePunchRule {

    @Override
    public boolean applies(Unit target, Battle battle) {
        return target.getState() instanceof NormalState;
    }

    @Override
    public void apply(Unit caster, Unit target, Battle battle) {
        int damage = battle.getDamagePolicy().compute(100, caster, target, battle);
        target.takeDamage(damage);

        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

        if (target.isDead()) {
            System.out.println(target.getDisplayName() + " 死亡。");
            battle.getDeathBus().notifyDeath(target, caster, battle);
        }
    }
}

