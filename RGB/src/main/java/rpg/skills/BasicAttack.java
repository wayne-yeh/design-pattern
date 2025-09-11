package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.OneEnemyPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

public class BasicAttack implements Action {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();

    @Override
    public String name() {
        return "普通攻擊";
    }

    @Override
    public int mpCost() {
        return 0;
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
        System.out.println(caster.getDisplayName() + " 攻擊 " + target.getDisplayName() + "。");

        int damage = battle.getDamagePolicy().compute(caster.getStrength(), caster, target, battle);
        target.takeDamage(damage);

        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

        if (target.isDead()) {
            System.out.println(target.getDisplayName() + " 死亡。");
            battle.getDeathBus().notifyDeath(target, caster, battle);
        }
    }
}


