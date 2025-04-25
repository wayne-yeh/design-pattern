package map.object.state;

import map.GameMap;
import map.object.State;
import map.object.Character;

import java.util.Random;
import java.util.Set;

public class TeleportState extends State {
    public TeleportState() {
        super(1);
    }

    @Override
    public void applyEffect(Character character) {
        Set<String> keys = GameMap.occupiedCoordinates.keySet();
        Random random = new Random();
        int mapWidth = GameMap.maxRow, mapHeight = GameMap.maxColumn;
        while (true) {
            int newX = random.nextInt(mapWidth);
            int newY = random.nextInt(mapHeight);
            String key = newX + "," + newY;
            if (!GameMap.occupiedCoordinates.containsKey(key)) {
                GameMap.occupiedCoordinates.remove(character.getX() + "," + character.getY());
                character.setX(newX);
                character.setY(newY);
                GameMap.occupiedCoordinates.put(key, character);
                System.out.println("瞬身移動至 (" + newX + ", " + newY + ")");
                break;
            }

            if (isExpired()) {
                System.out.println("狀態到期回復正常狀態");
                character.setState(new NormalState());
            }
        }

    }

    @Override
    public String getName() {
        return "Teleport";
    }
}

