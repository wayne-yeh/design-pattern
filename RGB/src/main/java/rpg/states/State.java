package rpg.states;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 角色狀態接口
 */
public interface State {
    /**
     * 回合開始時觸發
     * 
     * @param unit   角色
     * @param battle 戰鬥上下文
     */
    void onTurnStart(Unit unit, Battle battle);

    /**
     * 受到傷害時觸發，可以修改傷害值
     * 
     * @param unit   受傷角色
     * @param amount 原始傷害值
     * @param source 傷害來源
     * @param battle 戰鬥上下文
     * @return 實際傷害值
     */
    int onReceiveDamage(Unit unit, int amount, Unit source, Battle battle);

    /**
     * 剩餘回合數
     */
    int ticksRemaining();

    /**
     * 回合倒數
     */
    void tickDown();

    /**
     * 狀態名稱
     */
    String getName();

    /**
     * 是否能行動
     */
    boolean canAct();
}
