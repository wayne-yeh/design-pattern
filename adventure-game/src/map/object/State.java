package map.object;
import map.Object;

public abstract class State {

    protected int remainingTurns;

    public State(int duration) {
        this.remainingTurns = duration;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void decreaseTurn() {
        remainingTurns--;
    }

    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    public abstract void applyEffect(Object object);

    public abstract String getName();

    public Boolean shouldAttackBecomeNormal() {
        return false;
    }


}

