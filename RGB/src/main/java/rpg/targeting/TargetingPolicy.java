package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;


public interface TargetingPolicy {

    List<Unit> candidates(Unit caster, Battle battle);

    List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider);
}



