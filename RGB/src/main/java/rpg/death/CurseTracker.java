package rpg.death;

import rpg.battle.Battle;
import rpg.units.Unit;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class CurseTracker implements DeathListener {

    private Map<Unit, Set<Unit>> curses = new HashMap<>();

    public void addCurse(Unit target, Unit caster) {
        curses.computeIfAbsent(target, k -> new HashSet<>()).add(caster);
    }

    @Override
    public void onDeath(Unit dead, Unit killer, Battle battle) {
        Set<Unit> cursers = curses.get(dead);
        if (cursers != null && !cursers.isEmpty()) {
            int remainingMp = dead.getCurrentMp();

            for (Unit curser : cursers) {
                if (curser.isAlive()) {
                    curser.heal(remainingMp);
                    System.out.println(curser.getDisplayName() + " 因詛咒獲得 " + remainingMp + " HP。");
                }
            }

            curses.remove(dead);
        }
    }

    public boolean hasCurse(Unit target, Unit caster) {
        Set<Unit> cursers = curses.get(target);
        return cursers != null && cursers.contains(caster);
    }
}

