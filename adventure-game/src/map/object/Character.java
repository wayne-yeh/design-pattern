package map.object;

import map.object.state.Normal;

public class Character {

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
        switch (c) {
            case '↑':
                x--;
                break;
            case '↓':
                x++;
                break;
            case '←':
                y--;
                break;
            case '→':
                y++;
                break;
            default:
                System.out.println("無效指令: " + c);
        }
        System.out.println("角色現在位置: (" + x + ", " + y + ")");
    }




}
