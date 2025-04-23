package map.object.state;

import map.object.State;
import map.object.Character;

public class InvincibleState extends State {

    public InvincibleState() {
        super(2);
    }

    @Override
    public void applyEffect(Character character) {
        character.isInvincible = true;
    }

    @Override
    public String getName() {
        return "Invincible";
    }
}

