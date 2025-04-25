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

            character.applyStateEffect();

            System.out.print("請角色輸入方向（↑, ↓, ←, →）或 A 攻擊: ");
            char input = scanner.next().charAt(0);

            if (character.isLimitedAction != null && new String(character.isLimitedAction).indexOf(input) == -1) {
                System.out.println("你目前限制指令，只能輸入這些指令：" + new String(character.isLimitedAction));
                continue;
            } else if (input != '↑' && input != '↓' && input != '←' && input != '→' && input != 'A') {
                System.out.println("無效指令，請重新輸入！");
                continue;
            }


            if (input == '↑' || input == '↓' || input == '←' || input == '→') {
                character.move(input);
            } else if (input == 'A') {
                character.attack(monsters);
            }


            if (character.isTwoAction) {

                System.out.println("加速狀態再多執行一次動作");
                System.out.print("請角色輸入方向（↑, ↓, ←, →）或 A 攻擊: ");
                char input2 = scanner.next().charAt(0);
                if (input2 != '↑' && input2 != '↓' && input2 != '←' && input2 != '→' && input2 != 'A') {
                    System.out.println("無效指令，請重新輸入！");
                    continue;
                }
                if (input2 == '↑' || input2 == '↓' || input2 == '←' || input2 == '→') {
                    character.move(input2);
                } else if (input2 == 'A') {
                    character.attack(monsters);
                }
            }

            for (Monster monster: monsters) {
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
