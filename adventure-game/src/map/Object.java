package map;

import map.object.Character;
import map.object.State;
import map.object.Treasure;

public abstract class Object {

    int x;
    int y;
    char direction;

    public void touch(Treasure treasure, Object object) {
        object.setState(treasure.getState());
        System.out.println(Object.class.getSimpleName() + "狀態已變更為: " + treasure.getState().getClass().getSimpleName());
    }
    private void touchAndStay(int currentX, int currentY, char c, Object object) {
        System.out.printf("觸碰「%s」了%n", object.getClass().getSimpleName());
        System.out.println("停留在原地");
        this.x = currentX;
        this.y = currentY;
        direction = c;
        System.out.println("物件現在面向:" + direction);
        System.out.println("物件現在位置: (" + x + ", " + y + ")");
    }

    public abstract void setState(State state);


}
