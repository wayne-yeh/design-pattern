package rpg;

import rpg.battle.Battle;
import rpg.io.GameInputParser;
import java.util.Scanner;

/**
 * RPG 遊戲互動版主程式
 */
public class InteractiveMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== 歡迎來到 RPG 對戰遊戲 ===");
            System.out.println();

            // 提示輸入格式
            System.out.println("請按照以下格式輸入遊戲數據：");
            System.out.println("#軍隊-1-開始");
            System.out.println("角色名稱 HP MP STR 技能1 技能2 ...");
            System.out.println("#軍隊-1-結束");
            System.out.println("#軍隊-2-開始");
            System.out.println("角色名稱 HP MP STR 技能1 技能2 ...");
            System.out.println("#軍隊-2-結束");
            System.out.println("英雄決策1");
            System.out.println("英雄決策2");
            System.out.println("...");
            System.out.println();
            System.out.println("可用技能：水球、火球、自我治療、石化、下毒、召喚、自爆、鼓舞、詛咒、一拳攻擊");
            System.out.println();
            System.out.println("開始輸入數據（輸入完成後按 Ctrl+D (Mac/Linux) 或 Ctrl+Z (Windows)）：");
            System.out.println("----------------------------------------");

            // 解析輸入並創建戰鬥
            Battle battle = GameInputParser.parseInput(scanner);

            System.out.println("----------------------------------------");
            System.out.println("數據讀取完成，開始戰鬥！");
            System.out.println();

            // 開始戰鬥
            battle.start();

        } catch (Exception e) {
            System.err.println("遊戲執行錯誤: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
