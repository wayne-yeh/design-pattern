package rpg.skills;

import rpg.battle.Battle;
import rpg.death.CurseTracker;
import rpg.targeting.OneEnemyPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 詛咒技能
 */
public class Curse implements Skill {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();

    @Override
    public String name() {
        return "詛咒";
    }

    @Override
    public int mpCost() {
        return 100;
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
        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 使用了 詛咒。");

        // 註冊詛咒關係
        battle.getCurseTracker().addCurse(target, caster);
        System.out.println(target.getDisplayName() + " 被詛咒了！");
    }

}
