package rpg.core;

import rpg.battle.Battle;
import rpg.units.Unit;
import rpg.skills.Action;
import java.util.List;

/**
 * 決策提供者接口，用於決定角色的行動和目標選擇
 */
public interface DecisionProvider {
    /**
     * 選擇行動
     * 
     * @param unit   執行行動的角色
     * @param battle 戰鬥上下文
     * @return 選擇的行動
     */
    Action chooseAction(Unit unit, Battle battle);

    /**
     * 選擇目標
     * 
     * @param unit       執行行動的角色
     * @param candidates 候選目標列表
     * @param needed     需要選擇的目標數量
     * @return 選擇的目標列表
     */
    List<Unit> chooseTargets(Unit unit, List<Unit> candidates, int needed);
}



