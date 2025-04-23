package map.object.treasure;

import map.object.Character;
import map.object.Treasure;
import map.object.state.Teleport;

public class DokodemoDoor extends Treasure {
    public DokodemoDoor() {
        super(0.1, new Teleport());
    }

}
