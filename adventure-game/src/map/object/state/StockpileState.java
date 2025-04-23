package map.object.state;

import map.object.State;
import map.object.Character;

public class StockpileState extends State {
    public StockpileState() {
        super(2);
    }

    @Override
    public void applyEffect(Character character) {
        System.out.println("蓄力中... 回合剩餘：" + remainingTurns);
        // 被打中可中斷效果 → 可透過角色被攻擊時做邏輯處理（外部邏輯）
    }

    @Override
    public String getName() {
        return "Stockpile";
    }
}

