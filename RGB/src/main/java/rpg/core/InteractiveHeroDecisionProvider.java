package rpg.core;

import rpg.battle.Battle;
import rpg.skills.Action;
import rpg.skills.BasicAttack;
import rpg.units.Unit;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 互動式英雄決策提供者 - 即時輸入
 */
public class InteractiveHeroDecisionProvider implements DecisionProvider {
    private Scanner scanner;

    public InteractiveHeroDecisionProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Action chooseAction(Unit unit, Battle battle) {
        List<Action> availableActions = new ArrayList<>();
        availableActions.add(new BasicAttack());
        availableActions.addAll(unit.getSkills());

        while (true) {
            try {
                System.out.print(">> 請選擇行動 (輸入數字): ");
                System.out.flush(); // 確保提示訊息立即顯示

                if (!scanner.hasNextLine()) {
                    System.out.println("輸入結束，選擇普通攻擊");
                    return new BasicAttack();
                }

                String input = scanner.nextLine().trim();
                System.out.println("您選擇了: " + input);

                int actionIndex = Integer.parseInt(input);

                if (actionIndex >= 0 && actionIndex < availableActions.size()) {
                    return availableActions.get(actionIndex);
                } else {
                    System.out.println("❌ 無效的選擇，請輸入 0 到 " + (availableActions.size() - 1) + " 之間的數字");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 請輸入有效的數字");
            }
        }
    }

    @Override
    public List<Unit> chooseTargets(Unit unit, List<Unit> candidates, int needed) {
        List<Unit> selected = new ArrayList<>();

        if (candidates.isEmpty() || needed <= 0) {
            return selected;
        }

        while (selected.size() < needed) {
            try {
                System.out.print(">> 請選擇第 " + (selected.size() + 1) + " 個目標 (輸入數字): ");
                System.out.flush();

                if (!scanner.hasNextLine()) {
                    System.out.println("輸入結束，自動選擇剩餘目標");
                    break;
                }

                String input = scanner.nextLine().trim();
                System.out.println("您選擇了: " + input);

                int targetIndex = Integer.parseInt(input);

                if (targetIndex >= 0 && targetIndex < candidates.size()) {
                    Unit target = candidates.get(targetIndex);
                    if (!selected.contains(target)) {
                        selected.add(target);
                        System.out.println("✅ 已選擇: " + target.getDisplayName());
                    } else {
                        System.out.println("❌ 該目標已被選擇，請選擇其他目標");
                    }
                } else {
                    System.out.println("❌ 無效的選擇，請輸入 0 到 " + (candidates.size() - 1) + " 之間的數字");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 請輸入有效的數字");
            }
        }

        // 如果選擇的目標不足，補充前面的目標
        while (selected.size() < needed && selected.size() < candidates.size()) {
            for (Unit candidate : candidates) {
                if (!selected.contains(candidate)) {
                    selected.add(candidate);
                    System.out.println("🤖 自動選擇: " + candidate.getDisplayName());
                    break;
                }
            }
        }

        return selected;
    }
}

