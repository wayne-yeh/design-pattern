package map.object.state;

import map.object.State;
import map.object.Character;

public class HealingState extends State {

    public HealingState() {
        super(5);
    }

    @Override
    public void applyEffect(Character character) {
        int maxHp = 100;
        if (character.getHp() < maxHp) {
            character.setHp(Math.min(maxHp, character.getHp() + 30));
            System.out.println("恢復效果觸發，回復30HP，當前HP：" + character.getHp());
        } else {
            System.out.println("已滿血，自動恢復正常狀態");
            character.setState(null);
        }
    }

    @Override
    public String getName() {
        return "Healing";
    }
}

