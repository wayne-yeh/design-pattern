package command;

import equipment.Telecom;

public class TelecomDisconnectCommand implements Command{


    Telecom telecom;

    public TelecomDisconnectCommand(Telecom telecom){
        this.telecom = telecom;
    }

    @Override
    public void execute() {
        telecom.disConnect();
    }

    @Override
    public void undo() {
        telecom.connect();
    }
}
