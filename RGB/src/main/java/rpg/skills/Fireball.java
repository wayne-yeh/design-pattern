package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.AllEnemiesPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 火球技能
 */
public class Fireball implements Skill {
    private TargetingPolicy targetingPolicy = new AllEnemiesPolicy();

    @Override
    public String name() {
        return "火球";
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

        // 構建目標名稱列表
        StringBuilder targetNames = new StringBuilder();
        for (int i = 0; i < targets.size(); i++) {
            targetNames.append(targets.get(i).getDisplayName());
            if (i < targets.size() - 1) {
                targetNames.append(", ");
            }
        }

        System.out.println(caster.getDisplayName() + " 對 " + targetNames + " 使用了 火球。");

        // 按照順序處理每個目標
        for (Unit target : targets) {
            int damage = battle.getDamagePolicy().compute(50, caster, target, battle);
            target.takeDamage(damage);

            System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

            if (target.isDead()) {
                System.out.println(target.getDisplayName() + " 死亡。");
                battle.getDeathBus().notifyDeath(target, caster, battle);
            }
        }
    }
}
