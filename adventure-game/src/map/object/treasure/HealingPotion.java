package map.object.treasure;

import map.object.Character;
import map.object.Treasure;
import map.object.state.Healing;

public class HealingPotion extends Treasure {


    public HealingPotion() {
        super(0.15, new Healing());
    }


}
