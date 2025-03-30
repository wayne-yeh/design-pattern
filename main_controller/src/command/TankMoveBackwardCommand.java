package command;

import equipment.Tank;

public class TankMoveBackwardCommand implements Command {

    Tank tank;
    public TankMoveBackwardCommand(Tank tank){
        this.tank = tank;
    }


    @Override
    public void execute() {
        tank.moveBackward();
    }

    @Override
    public void undo() {
        tank.moveForward();
    }
}
