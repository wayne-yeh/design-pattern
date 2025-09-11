package rpg.death;

import rpg.battle.Battle;
import rpg.units.Unit;
import java.util.List;
import java.util.ArrayList;

/**
 * 死亡事件總線
 */
public class DeathBus {
    private List<DeathListener> listeners = new ArrayList<>();

    public void subscribe(DeathListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(DeathListener listener) {
        listeners.remove(listener);
    }

    public void notifyDeath(Unit dead, Unit killer, Battle battle) {
        for (DeathListener listener : listeners) {
            listener.onDeath(dead, killer, battle);
        }
    }
}


