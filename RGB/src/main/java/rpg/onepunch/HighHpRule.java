package rpg.onepunch;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 高血量規則：如果目標角色的生命值 ≥ 500，直接對目標角色造成 300 點傷害
 */
public class HighHpRule implements OnePunchRule {

    @Override
    public boolean applies(Unit target, Battle battle) {
        return target.getCurrentHp() >= 500;
    }

    @Override
    public void apply(Unit caster, Unit target, Battle battle) {
        int damage = battle.getDamagePolicy().compute(300, caster, target, battle);
        target.takeDamage(damage);

        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

        if (target.isDead()) {
            System.out.println(target.getDisplayName() + " 死亡。");
            battle.getDeathBus().notifyDeath(target, caster, battle);
        }
    }
}
