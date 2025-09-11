package rpg.death;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 死亡事件監聽器接口
 */
public interface DeathListener {
    /**
     * 當角色死亡時觸發
     * 
     * @param dead   死亡的角色
     * @param killer 殺死者（可能為null）
     * @param battle 戰鬥上下文
     */
    void onDeath(Unit dead, Unit killer, Battle battle);
}



