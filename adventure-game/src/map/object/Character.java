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

        int nextX = this.x;
        int nextY = this.y;
        String nextPosition = this.x + "," + this.y;
        System.out.println(GameMap.occupiedCoordinates);
        if (GameMap.occupiedCoordinates.containsKey(nextPosition)) {
            System.out.println("enter");
            Object object = GameMap.occupiedCoordinates.get(nextPosition);
            if (object instanceof Treasure) {
                System.out.println("觸碰到寶物了");
                System.out.println("寶物消失");
                touch();
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

    private void touchAndStay(int currentX, int currentY, char c, Object object) {
        System.out.printf("觸碰「%s」了%n", object.getClass().getSimpleName());
        System.out.println("停留在原地");
        this.x = currentX;
        this.y = currentY;
        direction = c;
        System.out.println("角色現在面向:" + direction);
        System.out.println("角色現在位置: (" + x + ", " + y + ")");
    }


    private void touch() {
    }

    public void attack(List<Monster> monsters) {
        Iterator<Monster> it = monsters.iterator();
        int i = 1;
        while (it.hasNext()) {
            Monster m = it.next();
            System.out.println(m.getName()+ " at (" + m.getX() + ", " + m.getY() + ")");

            // 上面方向：怪物跟主角同一欄，且怪物在主角上方
            if (direction == '↑' && m.getX() == x && m.getY() > y) {
                it.remove();
                System.out.println("擊殺怪物 at (" + m.getX() + ", " + m.getY() + ")");
                m.die();

            }

            // 下面方向：同一欄，怪物在主角下方
            else if (direction == '↓' && m.getX() == x && m.getY() < y) {
                it.remove();
                System.out.println("擊殺怪物 at (" + m.getX() + ", " + m.getY() + ")");
                m.die();
            }

            // 左邊方向：同一列，怪物在主角左邊
            else if (direction == '←' && m.getY() == y && m.getX() < x) {
                it.remove();
                System.out.println("擊殺怪物 at (" + m.getX() + ", " + m.getY() + ")");
                m.die();
            }

            // 右邊方向：同一列，怪物在主角右邊
            else if (direction == '→' && m.getY() == y && m.getX() > x) {
                it.remove();
                m.die();
                System.out.println("擊殺怪物 at (" + m.getX() + ", " + m.getY() + ")");
                m.die();
            } else if (i == monsters.size()){
                System.out.println("沒有擊殺任何怪物");
            }

            i++;
        }
    }


}
