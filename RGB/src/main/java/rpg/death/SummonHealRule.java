package rpg.death;

import rpg.battle.Battle;
import rpg.units.Unit;
import java.util.Map;
import java.util.HashMap;

/**
 * 召喚治癒規則
 */
public class SummonHealRule implements DeathListener {
    // 史萊姆 -> 召喚者的映射
    private Map<Unit, Unit> summonRelations = new HashMap<>();

    public void addSummonRelation(Unit slime, Unit summoner) {
        summonRelations.put(slime, summoner);
    }

    @Override
    public void onDeath(Unit dead, Unit killer, Battle battle) {
        Unit summoner = summonRelations.get(dead);
        if (summoner != null && summoner.isAlive()) {
            summoner.heal(30);
            System.out.println(summoner.getDisplayName() + " 因召喚物死亡獲得 30 HP。");
            summonRelations.remove(dead);
        }
    }
}
