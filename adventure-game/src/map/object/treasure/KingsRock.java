package map.object.treasure;

import map.object.Character;
import map.object.Treasure;
import map.object.state.StockpileState;

public class KingsRock extends Treasure {


    public KingsRock() {
        super(0.1, new StockpileState());
    }


}
