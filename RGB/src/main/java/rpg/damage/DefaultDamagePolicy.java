package rpg.damage;

import rpg.battle.Battle;
import rpg.states.CheerupState;
import rpg.units.Unit;

/**
 * 預設傷害計算策略
 */
public class DefaultDamagePolicy implements DamagePolicy {

    @Override
    public int compute(int baseDamage, Unit source, Unit target, Battle battle) {
        int actualDamage = baseDamage;

        // 受到鼓舞狀態：每一個能造成傷害的行動中，對於每一位被傷害者都額外創造 50 滴 HP 的加成傷害
        if (source.getState() instanceof CheerupState) {
            actualDamage += 50;
        }

        // 讓目標狀態處理傷害修正
        actualDamage = target.getState().onReceiveDamage(target, actualDamage, source, battle);

        return Math.max(0, actualDamage);
    }
}

