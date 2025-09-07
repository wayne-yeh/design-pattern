package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;

/**
 * 目標選擇策略接口
 */
public interface TargetingPolicy {
    /**
     * 獲取候選目標列表
     * 
     * @param caster 施法者
     * @param battle 戰鬥上下文
     * @return 候選目標列表
     */
    List<Unit> candidates(Unit caster, Battle battle);

    /**
     * 從候選目標中選擇目標
     * 
     * @param caster           施法者
     * @param candidates       候選目標列表
     * @param needed           需要選擇的目標數量
     * @param decisionProvider 決策提供者
     * @return 選擇的目標列表
     */
    List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider);
}
