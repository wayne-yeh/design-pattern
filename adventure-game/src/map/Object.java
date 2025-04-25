package map;

import map.object.Character;
import map.object.State;
import map.object.Treasure;

public abstract class Object {

    public boolean isTwoAction = false;
    public boolean isInvincible = false;
    public char[] isLimitedAction;
    public boolean isAttackNoLimit = false;
    public int maxHp;
    protected State state;
    protected int x; // row
    protected int y; // column
    protected int hp;

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {

    };

    protected void touch(Treasure treasure) {
        this.setState(treasure.getState());
        System.out.println(this.getClass().getSimpleName() + "狀態已變更為: " + treasure.getState().getClass().getSimpleName());
    }


}
