package rpg.damage;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 傷害計算策略接口
 */
public interface DamagePolicy {
    /**
     * 計算實際傷害值
     * 
     * @param baseDamage 基礎傷害
     * @param source     傷害來源
     * @param target     目標
     * @param battle     戰鬥上下文
     * @return 實際傷害值
     */
    int compute(int baseDamage, Unit source, Unit target, Battle battle);
}
