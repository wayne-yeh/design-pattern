package command;

import equipment.Tank;

public class TankMoveForwardCommand implements Command {

    Tank tank;
    public TankMoveForwardCommand(Tank tank){
        this.tank = tank;
    }
    @Override
    public void execute() {
        tank.moveForward();
    }

    @Override
    public void undo() {
        tank.moveBackward();
    }
}
