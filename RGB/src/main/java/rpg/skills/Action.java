package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;

/**
 * 行動接口，技能的基礎接口
 */
public interface Action {
    /**
     * 行動名稱
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
     * 執行行動
     * 
     * @param caster  執行者
     * @param targets 目標列表
     * @param battle  戰鬥上下文
     */
    void execute(Unit caster, List<Unit> targets, Battle battle);
}
