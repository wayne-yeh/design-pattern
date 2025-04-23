package map.object.state;

import map.object.State;
import map.object.Character;

public class EruptingState extends State {
    public EruptingState() {
        super(3);
    }

    @Override
    public void applyEffect(Character character) {
        System.out.println("爆發狀態中！攻擊會對全場造成 50 傷害！");
        // 由攻擊邏輯觸發全場攻擊（character.attackAllEnemies()）時判斷
    }

    @Override
    public String getName() {
        return "Erupting";
    }
}

