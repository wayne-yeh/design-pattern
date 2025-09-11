package rpg.core;

import rpg.battle.Battle;
import rpg.units.Unit;
import rpg.skills.Action;
import java.util.List;


public interface DecisionProvider {

    Action chooseAction(Unit unit, Battle battle);

    List<Unit> chooseTargets(Unit unit, List<Unit> candidates, int needed);
}



