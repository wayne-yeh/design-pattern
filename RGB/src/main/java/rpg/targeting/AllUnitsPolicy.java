package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;

/**
 * 所有角色目標策略（自爆技能使用）
 */
public class AllUnitsPolicy implements TargetingPolicy {

    @Override
    public List<Unit> candidates(Unit caster, Battle battle) {
        return battle.getAllUnits().stream()
                .filter(Unit::isAlive)
                .collect(java.util.ArrayList::new, java.util.ArrayList::add, java.util.ArrayList::addAll);
    }

    @Override
    public List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider) {
        return candidates; // 所有角色
    }
}

