package map.object;

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

    public abstract void applyEffect(Character character);

    public abstract String getName();
}

