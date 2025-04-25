package map.object.state;

import map.object.State;
import map.Object;

public class StockpileState extends State {
    public StockpileState() {
        super(2);
    }

    @Override
    public void applyEffect(Object object) {
        System.out.println("蓄力中預計變成爆發");
        decreaseTurn();
        System.out.println("當前狀態：蓄力（剩餘 " + remainingTurns + " 回合）");
        if (isExpired()) {
            System.out.println("狀態到期回復爆發狀態");
            object.setState(new EruptingState());
        }
    }

    @Override
    public String getName() {
        return "Stockpile";
    }
}

