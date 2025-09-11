package rpg.onepunch;

import rpg.battle.Battle;
import rpg.states.CheerupState;
import rpg.states.NormalState;
import rpg.units.Unit;


public class CheerupRule implements OnePunchRule {

    @Override
    public boolean applies(Unit target, Battle battle) {
        return target.getState() instanceof CheerupState;
    }

    @Override
    public void apply(Unit caster, Unit target, Battle battle) {
        int damage = battle.getDamagePolicy().compute(100, caster, target, battle);
        target.takeDamage(damage);

        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

        target.setState(new NormalState());
        System.out.println(target.getDisplayName() + " 的鼓舞狀態被移除了。");

        if (target.isDead()) {
            System.out.println(target.getDisplayName() + " 死亡。");
            battle.getDeathBus().notifyDeath(target, caster, battle);
        }
    }
}

