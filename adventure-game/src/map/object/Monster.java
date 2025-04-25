package map.object;

import map.GameMap;
import map.Object;
import map.object.state.NormalState;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Object {
    String symbol = "M";

    private static int idCounter = 1;
    private String name;
    State state = new NormalState();

    public Monster() {
        this.name = "怪物" + idCounter;
        setHp(1);
        idCounter++;
    }

    public String getName() {
        return name;
    }

    public void checkMoveOrAttack(Character character, List<Monster> monsters){

        if (isAdjacentTo(character)) {
            attack(character, monsters);
        } else {
            move();
        }

        if (isTwoAction) {
            if (isAdjacentTo(character)) {
                attack(character, monsters);
            } else {
                move();
            }
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

        char[] allowed = this.isLimitedAction;
        if (allowed != null && new String(allowed).indexOf(c) == -1) {
            System.out.println(this.name + " 因受限制不能往 " + c + " 移動");
            x = currentX;
            y = currentY;
            return;
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
    public void attack(Character character, List<Monster> monsters) {
        int cx = character.getX();
        int cy = character.getY();
        if (this.isAttackNoLimit) {
            Iterator<Monster> it = monsters.iterator();
            while (it.hasNext()) {
                Monster monster = it.next();

                if (monster != this) {
                    killMonster(it, monster);
                }
            }
            attackCharacter(character);
        }

        attackCharacter(character);
    }

    public void attackCharacter(Character character){
        if (isAdjacentTo(character)) {

            if (!character.isInvincible) {
                System.out.println("怪物攻擊主角！來自 (" + x + ", " + y + ") 減少50點生命值");
                character.underAttack();
                System.out.println("主角目前的生命值為 " + character.getHp());
            } else {

                System.out.println("無敵狀態傷害不了主角");
            }
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

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    protected void touchAndStay(int currentX, int currentY, Object object) {
        System.out.printf("觸碰「%s」了%n", object.getClass().getSimpleName());
        System.out.println("停留在原地");
        this.x = currentX;
        this.y = currentY;
        System.out.println("怪物現在位置: (" + x + ", " + y + ")");
    }

    public State getState() {
        return state;
    }
    private void killMonster(Iterator<Monster> it, Monster monster) {
        if (!monster.isInvincible) {
            it.remove();
            System.out.println("擊殺怪物 at (" + monster.getX() + ", " + monster.getY() + ")");
            monster.die();
            return;
        }
        System.out.println("無敵狀態沒有殺死怪物");
    }
}
