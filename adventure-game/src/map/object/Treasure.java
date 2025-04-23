package map.object;

import map.GameMap;
import map.Object;

public abstract class Treasure extends Object {

    String symbol = "x";
    private final double probability;
    int x = -1;
    int y = -1;
    private final State state;
    public Treasure(double probability, State state) {
        this.probability = probability;
        this.state = state;
    }

    public double getProbability() {
        return probability;
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

    public State getState() {
        return state;
    }

    public void onTouch(Character character){
        character.setState(this.getState());
        disappear();
    }

    private void disappear() {
        GameMap.occupiedCoordinates.remove(this.getX() +"," + this.getY());
        System.out.print(this.getClass().getSimpleName()+"消失了");
    }
}
