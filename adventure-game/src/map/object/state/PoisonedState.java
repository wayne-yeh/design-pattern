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
    }

    @Override
    public String getName() {
        return "Poisoned";
    }
}

