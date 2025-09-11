package rpg.skills;

import rpg.battle.Battle;
import rpg.states.PoisonedState;
import rpg.targeting.OneEnemyPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 下毒技能
 */
public class Poison implements Skill {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();

    @Override
    public String name() {
        return "下毒";
    }

    @Override
    public int mpCost() {
        return 80;
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
        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 使用了 下毒。");

        target.setState(new PoisonedState());
        System.out.println(target.getDisplayName() + " 中毒了！");
    }
}

