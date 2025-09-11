package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.OneEnemyPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;


public class Waterball implements Skill {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();

    @Override
    public String name() {
        return "水球";
    }

    @Override
    public int mpCost() {
        return 50;
    }

    @Override
    public TargetingPolicy targetingPolicy() {
        return targetingPolicy;
    }

    @Override
    public void execute(Unit caster, List<Unit> targets, Battle battle) {
        if (targets.isEmpty()) {
            return;
        }

        Unit target = targets.get(0);
        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 使用了 水球。");

        int damage = battle.getDamagePolicy().compute(120, caster, target, battle);
        target.takeDamage(damage);

        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

        if (target.isDead()) {
            System.out.println(target.getDisplayName() + " 死亡。");
            battle.getDeathBus().notifyDeath(target, caster, battle);
        }
    }
}

