package rpg.onepunch;

import rpg.battle.Battle;
import rpg.states.CheerupState;
import rpg.states.NormalState;
import rpg.units.Unit;

/**
 * 鼓舞規則：如果目標角色的當前狀態為受到鼓舞狀態，對目標角色造成 100 點傷害，並將目標角色的狀態恢復成正常狀態
 */
public class CheerupRule implements OnePunchRule {

    @Override
    public boolean applies(Unit target, Battle battle) {
        return target.getState() instanceof CheerupState;
    }

    @Override
    public void apply(Unit caster, Unit target, Battle battle) {
        int damage = battle.getDamagePolicy().compute(100, caster, target, battle);
        target.takeDamage(damage);

        System.out.println(caster.getDisplayName() + " 對 " + target.getDisplayName() + " 造成 " + damage + " 點傷害。");

        // 將狀態恢復成正常狀態
        target.setState(new NormalState());
        System.out.println(target.getDisplayName() + " 的鼓舞狀態被移除了。");

        if (target.isDead()) {
            System.out.println(target.getDisplayName() + " 死亡。");
            battle.getDeathBus().notifyDeath(target, caster, battle);
        }
    }
}

