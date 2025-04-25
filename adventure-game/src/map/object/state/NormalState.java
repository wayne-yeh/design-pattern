package map.object.state;

import map.object.State;
import map.object.Character;

public class NormalState extends State {

    public NormalState() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public void applyEffect(Character character) {
        character.isTwoAction =false;
        character.isInvincible = false;
        character.isAttackNoLimit = false;
        character.isLimitedAction = null;
        System.out.println("目前為正常狀態");
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
