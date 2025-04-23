package map.object.treasure;

import map.object.Character;
import map.object.Treasure;
import map.object.state.Healing;
import map.object.state.Orderless;

public class DevilFruit extends Treasure {
    public DevilFruit() {
        super(0.1, new Orderless());
    }


}
