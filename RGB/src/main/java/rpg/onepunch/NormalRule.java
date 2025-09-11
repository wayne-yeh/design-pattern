package rpg.onepunch;

import rpg.battle.Battle;
import rpg.states.NormalState;
import rpg.units.Unit;

/**
 * 正常規則：如果目標角色的當前狀態為正常狀態，對目標角色造成 100 點傷害
 */
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

