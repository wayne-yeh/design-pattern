package rpg;

import rpg.battle.Battle;
import rpg.io.GameInputParser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("歡迎使用 RPG 對戰遊戲！");
            System.out.println("提示：您可以複製貼上測試數據，或手動輸入");
            System.out.println();

            Battle battle = GameInputParser.parseInput(scanner);

            System.out.println(" 所有數據輸入完成，準備開始戰鬥！");
            System.out.println("========================================");

            battle.start();

        } catch (Exception e) {
            System.err.println("遊戲執行錯誤: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
