package rpg.skills;

import rpg.battle.Battle;
import rpg.targeting.TargetingPolicy;
import rpg.units.Unit;
import java.util.List;


public interface Action {

    String name();


    int mpCost();


    TargetingPolicy targetingPolicy();


    void execute(Unit caster, List<Unit> targets, Battle battle);
}



