package map.object.state;

import map.object.State;
import map.Object;

public class InvincibleState extends State {

    public InvincibleState() {
        super(2);
    }

    @Override
    public void applyEffect(Object object) {

        object.isInvincible = true;
        decreaseTurn();
        System.out.println("當前狀態：無敵（剩餘 " + remainingTurns + " 回合）");
        if (isExpired()) {
            System.out.println("狀態到期回復正常狀態");
            object.setState(new NormalState());
        }
    }

    @Override
    public String getName() {
        return "Invincible";
    }
}

