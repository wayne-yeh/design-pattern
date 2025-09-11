package rpg.skills;

import rpg.battle.Battle;
import rpg.states.PetrochemicalState;
import rpg.targeting.OneEnemyPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

public class Petrochemical implements Skill {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();

    @Override
    public String name() {
        return "石化";
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
        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 使用了 石化。");

        target.setState(new PetrochemicalState());
        System.out.println(target.getDisplayName() + " 被石化了！");
    }
}

