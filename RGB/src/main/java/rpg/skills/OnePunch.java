package rpg.skills;

import rpg.battle.Battle;
import rpg.onepunch.*;
import rpg.targeting.OneEnemyPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;
import java.util.Arrays;

/**
 * 一拳攻擊技能
 */
public class OnePunch implements Skill {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();
    private List<OnePunchRule> rules;

    public OnePunch() {
        // 按照優先級順序初始化規則
        this.rules = Arrays.asList(
                new HighHpRule(),
                new BadStateRule(),
                new CheerupRule(),
                new NormalRule());
    }

    @Override
    public String name() {
        return "一拳攻擊";
    }

    @Override
    public int mpCost() {
        return 180;
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
        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 使用了 一拳攻擊。");

        // 按照順序檢查規則，應用第一個符合條件的規則
        for (OnePunchRule rule : rules) {
            if (rule.applies(target, battle)) {
                rule.apply(caster, target, battle);
                return;
            }
        }
    }
}

