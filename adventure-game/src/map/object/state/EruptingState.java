package map.object.state;

import map.object.State;
import map.object.Character;

public class EruptingState extends State {
    public EruptingState() {
        super(3);
    }

    @Override
    public void applyEffect(Character character) {
        System.out.println("爆發狀態中 無地圖 無差別 攻擊");
        decreaseTurn();
        System.out.println("當前狀態：爆發（剩餘 " + remainingTurns + " 回合）");
        character.isAttackNoLimit = true;
        if (isExpired()) {
            System.out.println("狀態到期回復瞬身狀態");
            character.setState(new TeleportState());
        }
    }

    @Override
    public String getName() {
        return "Erupting";
    }
}

