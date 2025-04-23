package map.object;

import map.GameMap;
import map.Object;
import map.object.state.Normal;

import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Object {
    String symbol = "M";
    int x = -1;
    int y = -1;
    int hp = 1;
    private static int idCounter = 1;
    private String name;
    State state = new Normal();

    public Monster() {
        this.name = "怪物" + idCounter;
        idCounter++;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void checkMoveOrAttack(Character character){

        if (isAdjacentTo(character)) {
            attack(character);
        } else {
            move();
        }
    }

    public void move() {
        int currentX = this.x;
        int currentY = this.y;
        String currentPosition = this.x + "," + this.y;
        char[] directions = {'↑', '↓', '←', '→'};
        char c = directions[ThreadLocalRandom.current().nextInt(4)];

        switch (c) {
            case '↑':
                y++;
                break;
            case '↓':
                y--;
                break;
            case '←':
                x--;
                break;
            case '→':
                x++;
                break;
        }
        String nextPosition = this.x + "," + this.y;
        if (x < 0 || x > GameMap.maxRow || y < 0 || y > GameMap.maxColumn) {
            System.out.println("無法移動：超出地圖邊界");
            x = currentX;
            y = currentY;
            System.out.println("怪物現在位置: (" + x + ", " + y + ")");
            return;
        }

        if (GameMap.occupiedCoordinates.containsKey(nextPosition)) {
            System.out.println("enter");
            Object object = GameMap.occupiedCoordinates.get(nextPosition);
            if (object instanceof Treasure) {
                System.out.println("觸碰到寶物了");
                touch((Treasure) object);
                GameMap.occupiedCoordinates.remove(currentPosition);
                GameMap.occupiedCoordinates.put(nextPosition, this);
            } else if (object instanceof Character) {
                touchAndStay(currentX, currentY, object);
                return;
            } else if (object instanceof Obstacle) {
                touchAndStay(currentX, currentY, object);
                return;
            }
        }
        System.out.println("怪物移動成功");
        GameMap.occupiedCoordinates.remove(currentPosition);
        GameMap.occupiedCoordinates.put(nextPosition, this);
        System.out.println("怪物:"+ this.getName() +", 現在位置: (" + x + ", " + y + ")");
    }
    public void attack(Character character) {
        int cx = character.getX();
        int cy = character.getY();

        if (isAdjacentTo(character)) {
            System.out.println("怪物攻擊主角！來自 (" + x + ", " + y + ") 減少50點生命值");
            character.setHp(character.getHp() - 50);
            System.out.println("主角目前的生命值為 "+ character.getHp());
        }
    }

    public void die() {
        this.hp = 0;
        GameMap.occupiedCoordinates.remove(this.x+","+this.y);
        System.out.println(this.name + " 死亡");
        System.out.println("移除位置:"+this.x+","+this.y);
    }
    private boolean isAdjacentTo(Character character) {
        int cx = character.getX();
        int cy = character.getY();
        return (cx == x && Math.abs(cy - y) == 1) ||
                (cy == y && Math.abs(cx - x) == 1);
    }

    public void setState(State state) {
        this.state = state;
    }

    private void touch(Treasure treasure) {
        this.setState(treasure.getState());
        System.out.println("角色狀態已變更為: " + treasure.getState().getClass().getSimpleName());
    }

    private void touchAndStay(int currentX, int currentY, Object object) {
        System.out.printf("觸碰「%s」了%n", object.getClass().getSimpleName());
        System.out.println("停留在原地");
        this.x = currentX;
        this.y = currentY;
        System.out.println("怪物現在位置: (" + x + ", " + y + ")");
    }
}
