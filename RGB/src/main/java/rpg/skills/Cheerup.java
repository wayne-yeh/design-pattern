package rpg.skills;

import rpg.battle.Battle;
import rpg.states.CheerupState;
import rpg.targeting.NAlliesPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 鼓舞技能
 */
public class Cheerup implements Skill {
    private TargetingPolicy targetingPolicy = new NAlliesPolicy();

    @Override
    public String name() {
        return "鼓舞";
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

        // 構建目標名稱列表
        StringBuilder targetNames = new StringBuilder();
        for (int i = 0; i < targets.size(); i++) {
            targetNames.append(targets.get(i).getDisplayName());
            if (i < targets.size() - 1) {
                targetNames.append(", ");
            }
        }

        System.out.println(caster.getDisplayName() + " 對 " + targetNames + " 使用了 鼓舞。");

        // 對所有目標施加鼓舞狀態
        for (Unit target : targets) {
            target.setState(new CheerupState());
            System.out.println(target.getDisplayName() + " 受到了鼓舞！");
        }
    }
}
