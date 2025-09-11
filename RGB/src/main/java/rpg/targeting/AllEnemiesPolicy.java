package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;

/**
 * 所有敵軍目標策略
 */
public class AllEnemiesPolicy implements TargetingPolicy {

    @Override
    public List<Unit> candidates(Unit caster, Battle battle) {
        return battle.getEnemiesOf(caster);
    }

    @Override
    public List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider) {
        // 所有敵軍策略直接返回所有候選目標
        return candidates;
    }
}



