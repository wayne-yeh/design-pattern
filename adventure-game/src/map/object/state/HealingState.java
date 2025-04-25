package map.object.state;

import map.object.State;
import map.object.Character;

public class HealingState extends State {

    public HealingState() {
        super(5);
    }

    @Override
    public void applyEffect(Character character) {
        if (character.getHp() < character.maxHp) {
            character.setHp(Math.min(character.maxHp, character.getHp() + 30));
            System.out.println("恢復效果觸發，回復30HP，當前HP：" + character.getHp());
            decreaseTurn();
            System.out.println("當前狀態：恢復（剩餘 " + remainingTurns + " 回合）");
        } else {
            System.out.println("已滿血，自動恢復正常狀態");
            character.setState(new NormalState());
        }

        if (isExpired()) {
            System.out.println("狀態到期回復正常狀態");
            character.setState(new NormalState());
        }
    }

    @Override
    public String getName() {
        return "Healing";
    }
}

