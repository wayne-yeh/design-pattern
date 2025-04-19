package map;

import map.object.Monster;
import map.object.Obstacle;
import map.object.Treasure;
import map.object.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameMap {

    int row;
    int column;

    Obstacle obstacle = null;

    List<Treasure> generatedTreasures = new ArrayList<>();
    List<Treasure> generatedMonster = new ArrayList<>();

    List<Treasure> registeredTreasures;

    public GameMap(int row, int column, List<Treasure> treasures) {
        this.row = row;
        this.column = column;
        System.out.println("生成地圖: (" + row + ", " + column + ")");
        initialize();
        this.registeredTreasures = treasures;
    }

    private void initialize() {
        generateTreasure();
        generateMonster();
    }

    private void generateMonster() {

        int x = ThreadLocalRandom.current().nextInt(row);
        int y = ThreadLocalRandom.current().nextInt(column);
        Monster monster = new Monster();
        monster.setX(x);
        monster.setX(y);
    }


    private void generateTreasure(){
        Collections.shuffle(registeredTreasures);
        int i = 1;
        for (Treasure treasure : registeredTreasures) {
            if (Math.random() < treasure.getProbability()) {
                int x = ThreadLocalRandom.current().nextInt(row);
                int y = ThreadLocalRandom.current().nextInt(column);
                treasure.setX(x);
                treasure.setY(y);
                generatedTreasures.add(treasure);
                System.out.println("生成寶物: " + treasure.getClass().getSimpleName() + " at (" + x + ", " + y + ")");
                break;
            }

            if (registeredTreasures.size() == i) {
                System.out.println("沒有生成任何寶物");
            }
            i++;
        }

    }
}
