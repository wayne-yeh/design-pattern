package rpg;

import rpg.battle.Battle;
import rpg.io.GameInputParser;
import java.util.Scanner;

/**
 * RPG 遊戲主程式
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("🎮 歡迎使用 RPG 對戰遊戲！");
            System.out.println("💡 提示：您可以複製貼上測試數據，或手動輸入");
            System.out.println();

            // 解析輸入並創建戰鬥
            Battle battle = GameInputParser.parseInput(scanner);

            System.out.println("🎯 所有數據輸入完成，準備開始戰鬥！");
            System.out.println("========================================");

            // 開始戰鬥
            battle.start();

        } catch (Exception e) {
            System.err.println("❌ 遊戲執行錯誤: " + e.getMessage());
            System.err.println("💡 請檢查輸入格式是否正確");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
