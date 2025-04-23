package map.object;

import map.GameMap;
import map.Object;

import java.util.Iterator;
import java.util.List;


public class Character extends Object {

    String symbol = "↑→↓←";
    char direction;
    private int hp;
    private int x; // row
    private int y; // column
    State state = null;

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void move(char c) {
        int currentX = this.x;
        int currentY = this.y;
        String currentPosition = this.x + "," + this.y;

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
                default:
                System.out.println("無效指令: " + c);
            }

        if (x < 0 || x > GameMap.maxRow || y < 0 || y > GameMap.maxColumn) {
            System.out.println("無法移動：超出地圖邊界");
            System.out.println("角色現在面向:" + direction);
            x = currentX;
            y = currentY;
            System.out.println("角色現在位置: (" + x + ", " + y + ")");
            return;
        }
        String nextPosition = this.x + "," + this.y;
        System.out.println(GameMap.occupiedCoordinates);
        if (GameMap.occupiedCoordinates.containsKey(nextPosition)) {
            System.out.println("enter");
            Object object = GameMap.occupiedCoordinates.get(nextPosition);
            if (object instanceof Treasure) {
                System.out.println("觸碰到寶物了");
                touch((Treasure) object);
                GameMap.occupiedCoordinates.remove(currentPosition);
                GameMap.occupiedCoordinates.put(nextPosition, this);
            } else if (object instanceof Monster) {
                touchAndStay(currentX, currentY, c, object);
                return;
            } else if (object instanceof Obstacle) {
                touchAndStay(currentX, currentY, c, object);
                return;
            }
        }
        System.out.println("角色移動成功");
        GameMap.occupiedCoordinates.remove(currentPosition);
        GameMap.occupiedCoordinates.put(nextPosition, this);

        direction = c;
        System.out.println("角色現在面向:" + direction);
        System.out.println("角色現在位置: (" + x + ", " + y + ")");
    }

    private void touch(Treasure treasure) {
        this.setState(treasure.getState());
        System.out.println("角色狀態已變更為: " + treasure.getState().getClass().getSimpleName());
    }

    private void touchAndStay(int currentX, int currentY, char c, Object object) {
        System.out.printf("觸碰「%s」了%n", object.getClass().getSimpleName());
        System.out.println("停留在原地");
        this.x = currentX;
        this.y = currentY;
        direction = c;
        System.out.println("角色現在面向:" + direction);
        System.out.println("角色現在位置: (" + x + ", " + y + ")");
    }

    public void attack(List<Monster> monsters) {
        Iterator<Monster> it = monsters.iterator();
        int i = 1;
        boolean killed = false;
        int characterX = this.getX();
        int characterY = this.getY();

        while (it.hasNext()) {

            Monster monster = it.next();
            int monsterX = monster.getX();
            int monsterY = monster.getY();

            boolean blocked = false;
            if (direction == '↑' && monsterX == characterX && monsterY > characterY) {
                for (int y = characterY + 1; y < monsterY; y++) {
                    String eachPos = characterX + "," + y;
                    blocked = checkBlock(eachPos);
                    if (blocked) {
                        break;
                    }
                }
                if (!blocked){
                    killed = killMonster(it, monster);
                }
            } else if (direction == '↓' && monsterX == characterX && monsterY < characterY) {
                for (int y = characterY - 1; y > monsterY; y--) {
                    String eachPos = characterX + "," + y;
                    blocked = checkBlock(eachPos);
                    if (blocked) {
                        break;
                    }
                }
                if (!blocked){
                    killed = killMonster(it, monster);
                }
            } else if (direction == '←' && monsterY == characterY && monsterX < characterX) {
                for (int x = characterX - 1; x > monsterX; x--) {
                    String eachPos = characterX + "," + y;
                    blocked = checkBlock(eachPos);
                    if (blocked) {
                        break;
                    }
                }
                if (!blocked){
                    killed = killMonster(it, monster);
                }

            } else if (direction == '→' && monsterY == characterY && monsterX > characterX) {
                for (int x = characterX - 1; x > monsterX; x--) {
                    String eachPos = characterX + "," + y;
                    blocked = checkBlock(eachPos);
                    if (blocked) {
                        break;
                    }
                }
                if (!blocked){
                    killed = killMonster(it, monster);
                }
            }

            if (!killed && i == monsters.size()){
                System.out.println("沒有擊殺任何怪物");
            }
            i++;
        }
    }

    private boolean killMonster(Iterator<Monster> it, Monster monster) {
        it.remove();
        System.out.println("擊殺怪物 at (" + monster.getX() + ", " + monster.getY() + ")");
        monster.die();
        return true;
    }


    private boolean checkBlock(String key){

        if (GameMap.occupiedCoordinates.containsKey(key)
                && GameMap.occupiedCoordinates.get(key) instanceof Obstacle) {
            Obstacle obstacle = (Obstacle) GameMap.occupiedCoordinates.get(key);
            System.out.print("有障礙物無法擊殺 障礙物位置:" + obstacle.getX() +", "+obstacle.getY());
            return true;
        }
        return false;
    }

}
