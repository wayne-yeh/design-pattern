package map.object.state;

import map.object.State;
import map.object.Character;

public class PoisonedState extends State {
    public PoisonedState() {
        super(3);
    }

    @Override
    public void applyEffect(Character character) {
        character.setHp(Math.max(0, character.getHp() - 15));
        System.out.println("中毒效果觸發，扣除15HP，當前HP：" + character.getHp());
        decreaseTurn();
        System.out.println("當前狀態：中毒（剩餘 " + remainingTurns + " 回合）");
        if (isExpired()) {
            System.out.println("狀態到期回復正常狀態");
            character.setState(new NormalState());
        }
    }

    @Override
    public String getName() {
        return "Poisoned";
    }
}

