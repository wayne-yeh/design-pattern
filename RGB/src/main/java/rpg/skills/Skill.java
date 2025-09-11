package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 技能接口
 */
public interface Skill extends Action {
    /**
     * 技能名稱
     */
    String name();

    /**
     * MP 消耗
     */
    int mpCost();

    /**
     * 目標選擇策略
     */
    TargetingPolicy targetingPolicy();

    /**
     * 執行技能
     * 
     * @param caster  施法者
     * @param targets 目標列表
     * @param battle  戰鬥上下文
     */
    void execute(Unit caster, List<Unit> targets, Battle battle);
}



