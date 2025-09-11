package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;

/**
 * 單一敵軍目標策略
 */
public class OneEnemyPolicy implements TargetingPolicy {

    @Override
    public List<Unit> candidates(Unit caster, Battle battle) {
        return battle.getEnemiesOf(caster);
    }

    @Override
    public List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider) {
        return decisionProvider.chooseTargets(caster, candidates, needed);
    }
}



