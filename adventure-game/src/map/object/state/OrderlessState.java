package map.object.state;

import map.object.State;
import map.object.Character;

import java.util.Random;

import java.util.Random;

public class OrderlessState extends State {
    private int effectType; // 1 = 上下移動，2 = 左右移動

    public OrderlessState() {
        super(3);
        effectType = new Random().nextInt(2) + 1;
    }

    @Override
    public void applyEffect(Character character) {
        effectType = new Random().nextInt(2) + 1; // 每回合刷新效果
        String typeStr = effectType == 1 ? "只能上下移動" : "只能左右移動";
        System.out.println("混亂狀態效果觸發：" + typeStr);
    }

    @Override
    public String getName() {
        return "Orderless";
    }
}
