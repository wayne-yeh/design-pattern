import map.GameMap;
import map.object.Treasure;
import map.object.treasure.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Treasure> treasures = new ArrayList<>(List.of(new AcceleratingPotion(),
                new DevilFruit(),
                new DokodemoDoor(),
                new HealingPotion(),
                new KingsRock(),
                new Poison(),
                new SuperStar())
        );


        GameMap map = new GameMap(10, 10, treasures);
        System.out.println("Hello, World!");
    }
}