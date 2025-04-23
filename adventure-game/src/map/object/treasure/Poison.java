package map.object.treasure;

import map.object.Character;
import map.object.Treasure;
import map.object.state.Poisoned;

public class Poison extends Treasure {

    public Poison() {
        super(0.25, new Poisoned());
    }


}
