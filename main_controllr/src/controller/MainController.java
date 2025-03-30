package controller;

import command.Command;

import java.util.*;

public class MainController {

    char reset = 'R';
    char redo = 'U';
    char undo = 'Z';

    KeyBoard keyBoard = new KeyBoard();
    Command command;

    Map<Character, Command> commandMap = new HashMap<>();

    Stack<Character> s1 = new Stack<>();
    Stack<Character> s2 = new Stack<>();

    public void operate(char c){
        if (c == redo) {
            redo();
        } else if (c == undo) {
            undo();
        } else if (c == reset) {
            reset();
        } else {
            Command exCommand = commandMap.get(c);
            exCommand.execute();
            s1.push(c);
            s2.clear();
        }
    }

    public void changeDefaultCommand(String str){

    }

    public void setCommand(char c, Command command){

        if (c == redo || c == reset || c == undo) {
            throw new IllegalArgumentException("same as default command");
        }

        if (keyBoard.getKeys().contains(c)) {
            commandMap.put(c, command);
        } else {
            throw new IllegalArgumentException("no this letter");
        }
    }

    public void reset(){
        System.out.println("rest the controller");
        commandMap.clear();
    }

    public void undo(){

        if (s1.isEmpty()) {
            throw new IllegalArgumentException("no last step");
        }
        
        Character c = s1.pop();
        Command command = commandMap.get(c);
        command.undo();
        s2.push(c);
    }

    public void redo(){

        if (s2.isEmpty()) {
            throw new IllegalArgumentException("no next step");
        }

        char c = s2.pop();
        Command command = commandMap.get(c);
        command.execute();
        operate(c);
        s2.clear();
    }


}
