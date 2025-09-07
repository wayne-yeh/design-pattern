package rpg.skills;

import rpg.battle.Battle;
import rpg.core.AiDecisionProvider;
import rpg.death.SummonHealRule;
import rpg.targeting.SelfPolicy;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 召喚技能
 */
public class Summon implements Skill {
    private TargetingPolicy targetingPolicy = new SelfPolicy();

    @Override
    public String name() {
        return "召喚";
    }

    @Override
    public int mpCost() {
        return 150;
    }

    @Override
    public TargetingPolicy targetingPolicy() {
        return targetingPolicy;
    }

    @Override
    public void execute(Unit caster, List<Unit> targets, Battle battle) {
        System.out.println(caster.getDisplayName() + " 使用了 召喚。");

        // 創建史萊姆
        Unit slime = new Unit("Slime", 100, 0, 50, caster.getTroopId());
        slime.setDecisionProvider(new AiDecisionProvider());

        // 將史萊姆加入召喚者所屬軍隊
        if (caster.getTroopId() == 1) {
            battle.getTroop1().addUnit(slime);
        } else {
            battle.getTroop2().addUnit(slime);
        }

        // 註冊召喚關係
        battle.getSummonHealRule().addSummonRelation(slime, caster);

        System.out.println(caster.getDisplayName() + " 召喚了 " + slime.getDisplayName() + "！");
    }

}
