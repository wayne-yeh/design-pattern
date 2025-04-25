package map.object.state;

import map.object.State;
import map.object.Character;

public class AcceleratedState extends State {
    public AcceleratedState() {
        super(3);
    }

    @Override
    public void applyEffect(Character character) {
        character.isTwoAction = true;
        decreaseTurn();
        System.out.println("當前狀態：加速（剩餘 " + remainingTurns + " 回合）");
        if (isExpired()) {
            System.out.println("狀態到期回復正常狀態");
            character.setState(new NormalState());
        }
    }

    @Override
    public String getName() {
        return "Accelerated";
    }

    @Override
    public Boolean shouldAttackBecomeNormal() {
        return true;
    }


}

