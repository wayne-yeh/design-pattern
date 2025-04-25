package map.object.state;

import map.object.State;
import map.object.Character;
import map.Object;

import java.util.Random;

import java.util.Random;

public class OrderlessState extends State {
    private int effectType; // 1 = 上下移動，2 = 左右移動

    public OrderlessState() {
        super(3);
        effectType = new Random().nextInt(2) + 1;
    }

    @Override
    public void applyEffect(Object object) {
        boolean onlyUpDown = new Random().nextBoolean();

        if (onlyUpDown) {
            object.isLimitedAction = new char[]{'↑', '↓'};
            System.out.println("混亂狀態效果觸發：" + "只能上下移動");
        } else {
            object.isLimitedAction = new char[]{'→', '←'};
            System.out.println("混亂狀態效果觸發：" + "只能左右移動");
        }

        if (isExpired()) {
            System.out.println("狀態到期回復正常狀態");
            object.setState(new NormalState());
        }
    }

    @Override
    public String getName() {
        return "Orderless";
    }
}
