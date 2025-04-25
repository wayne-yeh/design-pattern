package map.object.state;

import map.object.State;
import map.Object;
public class PoisonedState extends State {
    public PoisonedState() {
        super(3);
    }

    @Override
    public void applyEffect(Object object) {

            object.setHp(Math.max(0, object.getHp() - 15));
            System.out.println("中毒效果觸發，扣除15HP，當前HP：" + object.getHp());
            decreaseTurn();
            System.out.println("當前狀態：中毒（剩餘 " + remainingTurns + " 回合）");
            if (isExpired()) {
                System.out.println("狀態到期回復正常狀態");
                object.setState(new NormalState());
            }

    }


    @Override
    public String getName() {
        return "Poisoned";
    }
}

