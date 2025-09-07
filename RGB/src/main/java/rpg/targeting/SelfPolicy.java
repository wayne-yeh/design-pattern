package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;
import java.util.Collections;

/**
 * 自己目標策略
 */
public class SelfPolicy implements TargetingPolicy {

    @Override
    public List<Unit> candidates(Unit caster, Battle battle) {
        return Collections.singletonList(caster);
    }

    @Override
    public List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider) {
        return candidates; // 直接返回自己
    }
}
