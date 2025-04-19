package map;

import map.object.*;
import map.object.Character;
import map.object.location.Location;
import map.object.state.Normal;

import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class GameMap {
    int row;
    int column;

    Obstacle obstacle = null;
    Set<String> occupiedCoordinates = new HashSet<>();
    List<Treasure> generatedTreasures = new ArrayList<>();
    List<Treasure> generatedMonster = new ArrayList<>();
    List<Treasure> registeredTreasures = new ArrayList<>();

    public GameMap(int row, int column, List<Treasure> treasures) {
        this.row = row;
        this.column = column;
        this.registeredTreasures = treasures;
        System.out.println("生成地圖: (" + row + ", " + column + ")");
        initialize();
    }

    private void initialize() {
        generateTreasure();
        generateMonster();
        generateCharacter();
    }

    private void generateCharacter() {

        Location location = getRandomAndSaveLocation();


        int defaultHp = 100;
        char defaultDirection = '→';
        State defaultState = new Normal();

        Character character = new Character();

        character.setX(location.getX());
        character.setY(location.getY());

        character.setHp(defaultHp);
        character.setState(defaultState);
        character.setDirection(defaultDirection);

    }



    private void generateMonster() {
        Location location = getRandomAndSaveLocation();
        Monster monster = new Monster();
        monster.setX(location.getX());
        monster.setY(location.getY());
        System.out.println("生成怪物: " + monster.getClass().getSimpleName() + " at (" + monster.getX() + ", " + monster.getY() + ")");

    }

    private void generateTreasure(){
        Collections.shuffle(registeredTreasures);
        int i = 1;
        int x, y = -1;

        for (Treasure treasure : registeredTreasures) {
            if (Math.random() < treasure.getProbability()) {
                Location location = getRandomAndSaveLocation();
                treasure.setX(location.getX());
                treasure.setY(location.getY());
                System.out.println("生成寶物: " + treasure.getClass().getSimpleName() + " at (" + x + ", " + y + ")");
                break;
            }

            if (registeredTreasures.size() == i) {
                System.out.println("沒有生成任何寶物");
            }
            i++;
        }

    }

    private Location getRandomAndSaveLocation() {
        int x, y;
        String position = null;
        Location location = new Location();
        do {
            x = ThreadLocalRandom.current().nextInt(row);
            y = ThreadLocalRandom.current().nextInt(column);
            position = x + "," + y;
        } while (occupiedCoordinates.contains(position));

        location.setX(x);
        location.setX(y);
        occupiedCoordinates.add(position);
        return location;
    }
}
