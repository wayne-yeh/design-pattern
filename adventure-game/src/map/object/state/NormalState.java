package map.object.state;

import map.object.State;
import map.Object;

public class NormalState extends State {

    public NormalState() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public void applyEffect(Object object) {
        object.isTwoAction =false;
        object.isInvincible = false;
        object.isAttackNoLimit = false;
        object.isLimitedAction = null;
        System.out.println("目前為正常狀態");
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
