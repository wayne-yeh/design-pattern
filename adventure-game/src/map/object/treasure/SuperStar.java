package map.object.treasure;

import map.object.Character;
import map.object.State;
import map.object.Treasure;
import map.object.state.InvincibleState;

public class SuperStar extends Treasure {


    public SuperStar() {
        super(0.1, new InvincibleState());
    }

}
