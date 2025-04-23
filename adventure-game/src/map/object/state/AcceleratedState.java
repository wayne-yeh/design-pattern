package map.object.state;

import map.object.State;
import map.object.Character;

public class AcceleratedState extends State {
    public AcceleratedState() {
        super(3);
    }

    @Override
    public void applyEffect(Character character) {
        // 不實作，應由 game loop 決定允許兩次行動
    }

    @Override
    public String getName() {
        return "Accelerated";
    }
}

