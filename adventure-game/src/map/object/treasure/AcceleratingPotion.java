package map.object.treasure;

import map.object.Character;
import map.object.Treasure;
import map.object.state.Accelerated;

public class AcceleratingPotion extends Treasure {


    public AcceleratingPotion() {
        super(0.2, new Accelerated());
    }
}
