package rpg;

import rpg.battle.Battle;
import rpg.battle.Troop;
import rpg.core.AiDecisionProvider;
import rpg.core.InteractiveHeroDecisionProvider;
import rpg.skills.*;
import rpg.units.Unit;
import java.util.Scanner;

/**
 * RPG 即時互動遊戲主程式
 */
public class RealTimeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== RPG 即時對戰遊戲 ===");
            System.out.println();

            // 創建英雄
            System.out.print("請輸入英雄名稱: ");
            String heroName = scanner.nextLine().trim();
            if (heroName.isEmpty())
                heroName = "英雄";

            System.out.print("請輸入英雄 HP (建議300): ");
            int heroHp = getIntInput(scanner, 300);

            System.out.print("請輸入英雄 MP (建議500): ");
            int heroMp = getIntInput(scanner, 500);

            System.out.print("請輸入英雄 STR (建議100): ");
            int heroStr = getIntInput(scanner, 100);

            Unit hero = new Unit(heroName, heroHp, heroMp, heroStr, 1);
            hero.setDecisionProvider(new InteractiveHeroDecisionProvider(scanner));

            // 讓玩家選擇技能
            addSkillsToHero(hero, scanner);

            // 創建軍隊
            Troop troop1 = new Troop(1);
            troop1.addUnit(hero);

            Troop troop2 = new Troop(2);
            createEnemyTroop(troop2, scanner);

            System.out.println();
            System.out.println("🎮 遊戲即將開始！");
            System.out.println("💡 提示：當需要選擇時，請輸入對應的數字");
            System.out.println();

            // 創建並開始戰鬥
            Battle battle = new Battle(troop1, troop2);
            battle.start();

        } catch (Exception e) {
            System.err.println("遊戲執行錯誤: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static int getIntInput(Scanner scanner, int defaultValue) {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("使用預設值: " + defaultValue);
                return defaultValue;
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("輸入無效，使用預設值: " + defaultValue);
            return defaultValue;
        }
    }

    private static void addSkillsToHero(Unit hero, Scanner scanner) {
        System.out.println();
        System.out.println("可選技能：");
        String[] skillNames = { "水球", "火球", "自我治療", "石化", "下毒", "召喚", "自爆", "鼓舞", "詛咒", "一拳攻擊" };
        for (int i = 0; i < skillNames.length; i++) {
            System.out.println((i + 1) + ". " + skillNames[i]);
        }

        System.out.print("請選擇技能 (輸入數字，用空格分隔，例如: 1 2 3): ");
        String skillInput = scanner.nextLine().trim();

        if (!skillInput.isEmpty()) {
            String[] skillIndices = skillInput.split("\\s+");
            for (String indexStr : skillIndices) {
                try {
                    int index = Integer.parseInt(indexStr) - 1;
                    if (index >= 0 && index < skillNames.length) {
                        Action skill = createSkill(skillNames[index]);
                        if (skill != null) {
                            hero.addSkill(skill);
                            System.out.println("✅ 已添加技能: " + skillNames[index]);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ 無效的技能選擇: " + indexStr);
                }
            }
        }

        if (hero.getSkills().isEmpty()) {
            // 預設添加一些技能
            hero.addSkill(new Fireball());
            hero.addSkill(new Waterball());
            System.out.println("🤖 自動添加預設技能: 火球、水球");
        }
    }

    private static void createEnemyTroop(Troop troop2, Scanner scanner) {
        System.out.println();
        System.out.print("要創建幾個敵人? (建議1-3): ");
        int enemyCount = getIntInput(scanner, 2);
        enemyCount = Math.max(1, Math.min(5, enemyCount)); // 限制在1-5之間

        for (int i = 1; i <= enemyCount; i++) {
            Unit enemy = new Unit("Enemy" + i, 150, 100, 40 + i * 5, 2);
            enemy.setDecisionProvider(new AiDecisionProvider());

            // 隨機添加技能
            if (i % 2 == 1) {
                enemy.addSkill(new Fireball());
            }
            if (i % 3 == 0) {
                enemy.addSkill(new Waterball());
            }

            troop2.addUnit(enemy);
            System.out.println("🤖 創建敵人: " + enemy.getDisplayName() +
                    " (HP:" + enemy.getCurrentHp() +
                    ", MP:" + enemy.getCurrentMp() +
                    ", STR:" + enemy.getStrength() + ")");
        }
    }

    private static Action createSkill(String skillName) {
        switch (skillName) {
            case "水球":
                return new Waterball();
            case "火球":
                return new Fireball();
            case "自我治療":
                return new SelfHealing();
            case "石化":
                return new Petrochemical();
            case "下毒":
                return new Poison();
            case "召喚":
                return new Summon();
            case "自爆":
                return new SelfExplosion();
            case "鼓舞":
                return new Cheerup();
            case "詛咒":
                return new Curse();
            case "一拳攻擊":
                return new OnePunch();
            default:
                return null;
        }
    }
}

