package game;

import map.GameMap;
import map.object.Character;
import map.object.Monster;
import map.object.Treasure;
import map.object.treasure.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    static List<Treasure> registerTreasures = new ArrayList<>();
    public static boolean isGameOver = false;
    public void start(){

        registerTreasures();
        GameMap map = new GameMap(3, 3, registerTreasures);
        Scanner scanner = new Scanner(System.in);
        Character character = map.getCharacter();
        List<Monster> monsters = map.getMonsters();

        while (true) {

            System.out.print("請角色輸入方向（↑, ↓, ←, →）或 A 攻擊: ");
            char input = scanner.next().charAt(0);

            if (input == 'A') {
                character.attack(monsters);
            } else {
                character.move(input);
            }

            for (Monster monster: monsters) {
                monster.checkMoveOrAttack(character);
                monster.checkMoveOrAttack(character);
                monster.checkMoveOrAttack(character);
                monster.checkMoveOrAttack(character);
                monster.checkMoveOrAttack(character);

            }

        }

    }

    private static void registerTreasures() {
        registerTreasures = new ArrayList<>(List.of(new AcceleratingPotion(),
                new DevilFruit(),
                new DokodemoDoor(),
                new HealingPotion(),
                new KingsRock(),
                new Poison(),
                new SuperStar())
        );
    }
}
