package rpg.targeting;

import rpg.battle.Battle;
import rpg.core.DecisionProvider;
import rpg.units.Unit;
import java.util.List;


public class NAlliesPolicy implements TargetingPolicy {

    @Override
    public List<Unit> candidates(Unit caster, Battle battle) {
        List<Unit> allies = battle.getAlliesOf(caster);
        allies.remove(caster);
        return allies;
    }

    @Override
    public List<Unit> select(Unit caster, List<Unit> candidates, int needed, DecisionProvider decisionProvider) {
        return decisionProvider.chooseTargets(caster, candidates, needed);
    }
}


