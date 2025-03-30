package controller;

import java.util.ArrayList;
import java.util.List;

public class KeyBoard {

    List<Character> keys = new ArrayList<>();

    public KeyBoard(){
        for (char c = 'A'; c <= 'Z'; c++) {
            keys.add(c);
        }
    }

    public List<Character> getKeys() {
        return keys;
    }

    public void setKeys(List<Character> keys) {
        this.keys = keys;
    }
}
