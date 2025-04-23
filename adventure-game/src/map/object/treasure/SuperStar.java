package map.object.treasure;

import map.object.Character;
import map.object.State;
import map.object.Treasure;
import map.object.state.Invincible;

public class SuperStar extends Treasure {

    State state = new Invincible();

    public SuperStar() {
        super(0.1, new Invincible());
    }

}
