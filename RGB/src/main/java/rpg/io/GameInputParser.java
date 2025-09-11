package rpg.io;

import rpg.battle.Battle;
import rpg.battle.Troop;
import rpg.core.AiDecisionProvider;
import rpg.core.HeroDecisionProvider;
import rpg.death.CurseTracker;
import rpg.death.SummonHealRule;
import rpg.skills.*;
import rpg.units.Unit;
import java.util.*;

/**
 * 遊戲輸入解析器
 */
public class GameInputParser {

    public static Battle parseInput(Scanner scanner) {
        System.out.println("=== RPG 遊戲數據輸入 ===");
        System.out.println();

        System.out.println("📝 輸入格式說明：");
        System.out.println("1. 軍隊標記：#軍隊-1-開始 / #軍隊-1-結束");
        System.out.println("2. 角色格式：角色名稱 HP MP STR [技能1] [技能2] ...");
        System.out.println("3. 可用技能：水球、火球、自我治療、石化、下毒、召喚、自爆、鼓舞、詛咒、一拳攻擊");
        System.out.println("4. 英雄決策：每行一個數字（行動選擇或目標選擇）");
        System.out.println();

        System.out.println("💡 範例輸入：");
        System.out.println("#軍隊-1-開始");
        System.out.println("英雄 300 500 100 火球 水球");
        System.out.println("#軍隊-1-結束");
        System.out.println("#軍隊-2-開始");
        System.out.println("Slime1 200 60 49 火球");
        System.out.println("Slime2 200 200 50 火球 水球");
        System.out.println("#軍隊-2-結束");
        System.out.println("1");
        System.out.println("2");
        System.out.println("1");
        System.out.println("...");
        System.out.println();
        System.out.println(" 現在開始輸入數據：");
        System.out.println("----------------------------------------");

        System.out.println("請輸入第一軍隊數據（英雄軍隊）：");
        Troop troop1 = parseTroop(scanner, 1);
        System.out.println("第一軍隊解析完成！");
        System.out.println();

        System.out.println("請輸入第二軍隊數據（敵軍）：");
        Troop troop2 = parseTroop(scanner, 2);
        System.out.println("第二軍隊解析完成！");
        System.out.println();

        System.out.println("請輸入英雄決策數據（每行一個數字）：");
        System.out.println("提示：這些數字將用於英雄的行動和目標選擇");
        List<String> heroDecisions = parseHeroDecisions(scanner);
        System.out.println("英雄決策解析完成！");
        System.out.println();

        Unit hero = troop1.getHero();
        if (hero != null) {
            hero.setDecisionProvider(new HeroDecisionProvider(heroDecisions));
        }

        setAiDecisionProviders(troop1, troop2);

        Battle battle = new Battle(troop1, troop2);

        return battle;
    }

    private static Troop parseTroop(Scanner scanner, int troopId) {
        System.out.println(">> 請輸入軍隊標記開始 (例：#軍隊-" + troopId + "-開始)");

        String startLine = scanner.nextLine();
        System.out.println("   輸入：" + startLine);
        while (!startLine.contains("#軍隊-" + troopId + "-開始")) {
            System.out.println("❌ 格式錯誤，請重新輸入：#軍隊-" + troopId + "-開始");
            startLine = scanner.nextLine();
            System.out.println("   輸入：" + startLine);
        }

        Troop troop = new Troop(troopId);
        int unitCount = 0;

        System.out.println(">> 請輸入角色數據（格式：角色名稱 HP MP STR [技能1] [技能2] ...）");
        if (troopId == 1) {
            System.out.println("   💡 第一個角色必須是英雄");
            System.out.println("   📝 範例：[英雄 300 500 100 火球 水球]");
        } else {
            System.out.println("   📝 範例：[Boss 300 150 80 火球 石化], [Slime1 200 60 49 火球], [Dragon 500 200 150 一拳攻擊 自爆]");
        }
        System.out.println("   🎯 可用技能：水球、火球、自我治療、石化、下毒、召喚、自爆、鼓舞、詛咒、一拳攻擊");

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            System.out.println("   輸入：" + line);

            if (line.contains("#軍隊-" + troopId + "-結束")) {
                break;
            }

            if (line.trim().isEmpty()) {
                System.out.println("   💡 請輸入軍隊結束標記 (例：#軍隊-" + troopId + "-結束) 或繼續添加角色");
                continue;
            }

            if (!line.trim().isEmpty()) {
                try {
                    Unit unit = parseUnit(line, troopId);
                    troop.addUnit(unit);
                    unitCount++;
                    System.out.println("   ✅ 成功添加角色：" + unit.getDisplayName() +
                            " (HP:" + unit.getCurrentHp() +
                            ", MP:" + unit.getCurrentMp() +
                            ", STR:" + unit.getStrength() +
                            ", 技能數量:" + unit.getSkills().size() + ")");
                } catch (Exception e) {
                    System.out.println("   ❌ 角色數據格式錯誤：" + e.getMessage());
                    System.out.println("   💡 正確格式：角色名稱 HP MP STR [技能1] [技能2] ...");
                }
            }
        }

        System.out.println("   軍隊 " + troopId + " 共添加了 " + unitCount + " 個角色");
        return troop;
    }

    private static Unit parseUnit(String line, int troopId) {
        String[] parts = line.trim().split("\\s+");

        String name = parts[0];
        int hp = Integer.parseInt(parts[1]);
        int mp = Integer.parseInt(parts[2]);
        int str = Integer.parseInt(parts[3]);

        Unit unit = new Unit(name, hp, mp, str, troopId);

        // 解析技能
        for (int i = 4; i < parts.length; i++) {
            Action skill = createSkill(parts[i]);
            if (skill != null) {
                unit.addSkill(skill);
            }
        }

        return unit;
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

    private static List<String> parseHeroDecisions(Scanner scanner) {
        List<String> decisions = new ArrayList<>();

        System.out.println(">> 請輸入英雄決策數字（每行一個）");
        System.out.println("   💡 決策類型說明：");
        System.out.println("   - 行動選擇：0=普通攻擊, 1=第1個技能, 2=第2個技能, ...");
        System.out.println("   - 目標選擇：0=第1個目標, 1=第2個目標, 2=第3個目標, ...");
        System.out.println("   📝 常用範例：[0], [1], [2], [1], [0], [1] ...");
        System.out.println("   🎯 建議輸入5-10個決策數字");
        System.out.println("   🚪 離開方式：輸入 'end' 或按 Ctrl+D (Mac/Linux) / Ctrl+Z (Windows)");

        int decisionCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                // 檢查離開條件
                if (line.equalsIgnoreCase("end") || line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                    System.out.println("   🚪 輸入結束");
                    break;
                }

                try {
                    // 驗證是否為數字
                    int value = Integer.parseInt(line);
                    if (value >= 0 && value <= 10) { // 合理的範圍
                        decisions.add(line);
                        decisionCount++;
                        System.out.println("   ✅ 決策 " + decisionCount + "：" + line);
                    } else {
                        System.out.println("   ❌ 數字超出範圍：" + line + " (建議使用 0-10)");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("   ❌ 無效數字：" + line + " (例：0, 1, 2, ...) 或輸入 'end' 結束");
                }
            }
        }

        System.out.println("   總共輸入了 " + decisionCount + " 個決策");
        return decisions;
    }

    private static void setAiDecisionProviders(Troop troop1, Troop troop2) {
        for (int i = 1; i < troop1.getUnits().size(); i++) {
            troop1.getUnits().get(i).setDecisionProvider(new AiDecisionProvider());
        }

        for (Unit unit : troop2.getUnits()) {
            unit.setDecisionProvider(new AiDecisionProvider());
        }
    }
}
