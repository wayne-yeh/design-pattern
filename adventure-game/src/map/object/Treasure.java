package map.object;

import map.Object;

public abstract class Treasure extends Object {

    String symbol = "x";
    private final double probability;
    int x = -1;
    int y = -1;

    public Treasure(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public abstract void onTouch();

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
}
