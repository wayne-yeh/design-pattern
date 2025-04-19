package map.object;

import map.object.state.Normal;

public class Character {

    String symbol = "↑→↓←";
    private int hp;
    State state = new Normal();
    private int x; // row
    private int y; // column

    public Character(int x, int y, int defultHp) {
        this.x = x;
        this.y = y;
        this.hp = defultHp;
    }
    public int getHp() {
        return hp;
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

    public int getX() { return x; }
    public int getY() { return y; }
}
