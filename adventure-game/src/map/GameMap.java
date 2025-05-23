package map;

import map.object.*;
import map.object.Character;
import map.object.location.Location;
import map.object.state.NormalState;

import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class GameMap {


    public static int maxRow;
    public static int maxColumn;
    public Character character;
    Obstacle obstacle;
    List<Monster> monsters = new ArrayList<>();

    public static Map<String, Object> occupiedCoordinates = new HashMap<>();
    List<Treasure> registeredTreasures = new ArrayList<>();

    public GameMap(int row, int column, List<Treasure> treasures) {
        this.maxRow = row;
        this.maxColumn = column;
        this.registeredTreasures = treasures;
        System.out.println("生成地圖: (" + row + ", " + column + ")");
        initialize();
    }

    private void initialize() {
        generateTreasure();
        generateMonster();
        generateObstacle();
        generateCharacter();
    }

    public void generateObstacle(){
        obstacle = new Obstacle();
        Location location = getRandomAndSaveLocation(obstacle);
        obstacle.setX(location.getX());
        obstacle.setY(location.getY());
        System.out.println("生成障礙物: " + obstacle.getClass().getSimpleName() + " at (" + obstacle.getX() + ", " + obstacle.getY() + ")");
    }

    public void generateCharacter() {
        character = new Character();
        Location location = getRandomAndSaveLocation(character);

        character.setX(location.getX());
        character.setY(location.getY());
        System.out.println("角色: " + character.getClass().getSimpleName() + " at (" + character.getX() + ", " + character.getY() + ")");
        System.out.println("角色的HP : " + character.getHp());
        System.out.println("角色的面向 : " + character.getDirection());
        System.out.println("角色的狀態: " + character.getState().getClass().getSimpleName());

    }



    public void generateMonster() {
        Monster monster = new Monster();
        Location location = getRandomAndSaveLocation(monster);

        monster.setX(location.getX());
        monster.setY(location.getY());
        System.out.println("生成怪物: " + monster.getName() + " at (" + monster.getX() + ", " + monster.getY() + ")");
        monsters.add(monster);
    }

    public void generateTreasure(){
        Collections.shuffle(registeredTreasures);
        int i = 1;
        int x, y = -1;

        for (Treasure treasure : registeredTreasures) {
            if (Math.random() < treasure.getProbability()) {
                Location location = getRandomAndSaveLocation(treasure);
                treasure.setX(location.getX());
                treasure.setY(location.getY());
                System.out.println("生成寶物: " + treasure.getClass().getSimpleName() + " at (" + treasure.getX() + ", " + treasure.getY() + ")");
                break;
            }

            if (registeredTreasures.size() == i) {
                System.out.println("沒有生成任何寶物");
            }
            i++;
        }

    }

    private Location getRandomAndSaveLocation(Object object) {
        int x, y;
        String position = null;
        Location location = new Location();
        do {
            x = ThreadLocalRandom.current().nextInt(maxRow);
            y = ThreadLocalRandom.current().nextInt(maxColumn);
            position = x + "," + y;
        } while (occupiedCoordinates.containsKey(position));

        location.setX(x);
        location.setY(y);
        occupiedCoordinates.put(position, object);
        return location;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
