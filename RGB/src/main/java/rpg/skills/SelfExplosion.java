package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.AllUnitsPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 自爆技能
 */
public class SelfExplosion implements Skill {
    private TargetingPolicy targetingPolicy = new AllUnitsPolicy();

    @Override
    public String name() {
        return "自爆";
    }

    @Override
    public int mpCost() {
        return 200;
    }

    @Override
    public TargetingPolicy targetingPolicy() {
        return targetingPolicy;
    }

    @Override
    public void execute(Unit caster, List<Unit> targets, Battle battle) {
        // 構建目標名稱列表
        StringBuilder targetNames = new StringBuilder();
        for (int i = 0; i < targets.size(); i++) {
            targetNames.append(targets.get(i).getDisplayName());
            if (i < targets.size() - 1) {
                targetNames.append(", ");
            }
        }

        System.out.println(caster.getDisplayName() + " 對 " + targetNames + " 使用了 自爆。");

        // 行動角色自殺
        caster.setCurrentHp(0);
        System.out.println(caster.getDisplayName() + " 自爆了！");
        battle.getDeathBus().notifyDeath(caster, caster, battle);

        // 對所有角色造成傷害
        for (Unit target : targets) {
            int damage = battle.getDamagePolicy().compute(150, caster, target, battle);
            target.takeDamage(damage);

            System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

            if (target.isDead()) {
                System.out.println(target.getDisplayName() + " 死亡。");
                battle.getDeathBus().notifyDeath(target, caster, battle);
            }
        }
    }
}
