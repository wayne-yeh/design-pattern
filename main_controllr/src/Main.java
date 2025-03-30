import command.TankMoveBackwardCommand;
import command.TankMoveForwardCommand;
import command.TelecomConnectCommand;
import command.TelecomDisconnectCommand;
import controller.MainController;
import equipment.Tank;
import equipment.Telecom;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MainController mainController = new MainController();
        Tank tank = new Tank();
        Telecom telecom = new Telecom();
        System.out.println("set up Controller");
        Scanner setUp = new Scanner(System.in);

        System.out.print("set up TankMoveForwardCommand: ");
        mainController.setCommand(setUp.nextLine().charAt(0), new TankMoveForwardCommand(tank));
        System.out.print("set up TankMoveBackCommand: ");
        mainController.setCommand(setUp.nextLine().charAt(0), new TankMoveBackwardCommand(tank));
        System.out.print("set up TelecomConnectCommand: ");
        mainController.setCommand(setUp.nextLine().charAt(0), new TelecomConnectCommand(telecom));
        System.out.print("set up TelecomDisconnectCommand: ");
        mainController.setCommand(setUp.nextLine().charAt(0), new TelecomDisconnectCommand(telecom));

        System.out.println("start");

        Scanner operate = new Scanner(System.in);

        while (true) {

            System.out.print("operation:");

            String op = operate.nextLine();
            mainController.operate(op.charAt(0));

            if (op.equals("exit")) {
                break;
            }
        }
    }
}