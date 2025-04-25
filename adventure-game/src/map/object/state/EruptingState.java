package map.object.state;

import map.object.State;
import map.Object;

public class EruptingState extends State {
    public EruptingState() {
        super(3);
    }

    @Override
    public void applyEffect(Object object) {
        System.out.println("爆發狀態中 無地圖 無差別 攻擊");
        decreaseTurn();
        System.out.println("當前狀態：爆發（剩餘 " + remainingTurns + " 回合）");
        object.isAttackNoLimit = true;
        if (isExpired()) {
            System.out.println("狀態到期回復瞬身狀態");
            object.setState(new TeleportState());
        }
    }

    @Override
    public String getName() {
        return "Erupting";
    }
}

