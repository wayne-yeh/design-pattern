package controller;

import command.Command;

import java.util.*;

public class MainController {

    char reset = 'R';
    char redo = 'U';
    char undo = 'Z';
    char recordMacro = 'M';
    char useMacro = 'Q';

    KeyBoard keyBoard = new KeyBoard();
    Command command;

    Map<Character, Command> commandBindingMap = new HashMap<>();
    List<Character> macroList = new ArrayList<>();

    Stack<Character> s1 = new Stack<>();
    Stack<Character> s2 = new Stack<>();
    Map<Character, String> defaultCommands = new HashMap<>();

    public MainController() {
        defaultCommands.put(reset, "reset");
        defaultCommands.put(redo, "redo");
        defaultCommands.put(undo, "undo");
        defaultCommands.put(recordMacro, "recordMacro");
        defaultCommands.put(useMacro, "useMacro");
    }

    public void operate(char c){
        if (c == redo) {
            redo();
        } else if (c == undo) {
            undo();
        } else if (c == reset) {
            reset();
        } else if (c == recordMacro){
            recordMacro();
        } else if (c == useMacro) {
            useMacro();
        } else {
            Command exCommand = commandBindingMap.get(c);
            exCommand.execute();
            s1.push(c);
            s2.clear();
        }
    }

    private void useMacro() {
        for (char c : macroList) {
            operate(c);
        }
    }

    private void recordMacro() {
        System.out.println("start to record ");
        System.out.println("type exit to finish macro ");
        Scanner src = new Scanner(System.in);
        while (true) {
            String str = src.nextLine();

            if (str.equals("exit")){
                break;
            }
            char shortcut = str.charAt(0);

            if (shortcut == reset || shortcut == recordMacro || shortcut == useMacro) {
                throw new IllegalArgumentException("not support this command");
            } else if (!keyBoard.getKeys().contains(shortcut)) {
                throw new IllegalArgumentException("no this letter");
            }

            String commmand;
            if (commandBindingMap.get(shortcut) != null){
                commmand = commandBindingMap.get(shortcut).getClass().getName();
            } else {
                commmand = defaultCommands.get(shortcut);
            }

            System.out.printf("use %s record this command %s \n", shortcut,commmand);
            macroList.add(shortcut);

        }
        System.out.println("finish record");
    }

    public void changeDefaultCommand(String str){

    }

    public void setCommand(char c, Command command){

        checkCommand(c);
        commandBindingMap.put(c, command);
    }

    private void checkCommand(char c) {
        if (c == redo || c == reset || c == undo || c == recordMacro || c == useMacro) {
            throw new IllegalArgumentException("same as default command");
        } else if (!keyBoard.getKeys().contains(c)) {
            throw new IllegalArgumentException("no this letter");
        }
    }

    public void reset(){
        System.out.println("rest the controller");
        commandBindingMap.clear();
    }

    public void undo(){

        if (s1.isEmpty()) {
            throw new IllegalArgumentException("no last step");
        }
        
        Character c = s1.pop();
        Command command = commandBindingMap.get(c);
        command.undo();
        s2.push(c);
    }

    public void redo(){

        if (s2.isEmpty()) {
            throw new IllegalArgumentException("no next step");
        }

        char c = s2.pop();
        Command command = commandBindingMap.get(c);
        command.execute();
        s2.clear();
    }


}
