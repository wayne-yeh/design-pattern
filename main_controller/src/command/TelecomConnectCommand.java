package command;

import equipment.Telecom;

public class TelecomConnectCommand implements Command {

    Telecom telecom;

    public TelecomConnectCommand(Telecom telecom){
        this.telecom = telecom;
    }

    @Override
    public void execute() {
        telecom.connect();
    }

    @Override
    public void undo() {
        telecom.disConnect();
    }
}
