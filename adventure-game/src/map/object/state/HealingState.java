package map.object.state;

import map.object.State;
import map.Object;

public class HealingState extends State {

    public HealingState() {
        super(5);
    }

    @Override
    public void applyEffect(Object object) {
        if (object.getHp() < object.maxHp) {
            object.setHp(Math.min(object.maxHp, object.getHp() + 30));
            System.out.println("恢復效果觸發，回復30HP，當前HP：" + object.getHp());
            decreaseTurn();
            System.out.println("當前狀態：恢復（剩餘 " + remainingTurns + " 回合）");
        } else {
            System.out.println("已滿血，自動恢復正常狀態");
            object.setState(new NormalState());
        }

        if (isExpired()) {
            System.out.println("狀態到期回復正常狀態");
            object.setState(new NormalState());
        }
    }

    @Override
    public String getName() {
        return "Healing";
    }
}

