package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.SelfPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 自我治療技能
 */
public class SelfHealing implements Skill {
    private TargetingPolicy targetingPolicy = new SelfPolicy();

    @Override
    public String name() {
        return "自我治療";
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
        System.out.println(caster.getDisplayName() + " 使用了 自我治療。");

        caster.heal(150);
        System.out.println(caster.getDisplayName() + " 恢復了 150 HP。");
    }
}
