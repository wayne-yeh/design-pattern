package map.object;

import map.GameMap;
import map.Object;

import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Object {
    String symbol = "M";
    int x = -1;
    int y = -1;
    int hp = 1;
    private static int idCounter = 1;
    private String name;
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
        System.out.println("怪物移動成功");
        GameMap.occupiedCoordinates.remove(currentPosition);
        GameMap.occupiedCoordinates.put(nextPosition, this);
        System.out.println("怪物:"+ this.getClass() +", 現在位置: (" + x + ", " + y + ")");
    }
    public void attack(Character hero) {
        int hx = hero.getX();
        int hy = hero.getY();

        if ((hx == x && Math.abs(hy - y) == 1) ||
                (hy == y && Math.abs(hx - x) == 1)) {

            System.out.println("怪物攻擊主角！來自 (" + x + ", " + y + ") 減少50點生命值");
            hero.setHp(hero.getHp() - 50);
            System.out.println("主角目前的生命值為 "+ hero.getHp());

        }
    }

    public void die() {
        this.hp = 0;
       GameMap.occupiedCoordinates.remove(this.x+","+this.y);

        System.out.println(this.name + " 死亡");
        System.out.println("移除位置:"+this.x+","+this.y);

    }
}
