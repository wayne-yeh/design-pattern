package rpg.onepunch;

import rpg.battle.Battle;
import rpg.units.Unit;

/**
 * 一拳攻擊規則接口
 */
public interface OnePunchRule {
    /**
     * 檢查規則是否適用
     * 
     * @param target 目標角色
     * @param battle 戰鬥上下文
     * @return 是否適用
     */
    boolean applies(Unit target, Battle battle);

    /**
     * 應用規則效果
     * 
     * @param caster 施法者
     * @param target 目標角色
     * @param battle 戰鬥上下文
     */
    void apply(Unit caster, Unit target, Battle battle);
}
