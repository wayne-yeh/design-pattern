package map.object.state;

import map.object.State;
import map.object.Character;

public class NormalState extends State {

    public NormalState() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public void applyEffect(Character character) {
        System.out.println("no effect");
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
