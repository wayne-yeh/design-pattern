package rpg.core;

import rpg.battle.Battle;
import rpg.skills.Action;
import rpg.skills.BasicAttack;
import rpg.units.Unit;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 英雄決策提供者
 */
public class HeroDecisionProvider implements DecisionProvider {
    private Queue<String> decisions;

    public HeroDecisionProvider(List<String> decisionList) {
        this.decisions = new LinkedList<>(decisionList);
    }

    @Override
    public Action chooseAction(Unit unit, Battle battle) {
        List<Action> availableActions = new ArrayList<>();
        availableActions.add(new BasicAttack());
        availableActions.addAll(unit.getSkills());

        if (decisions.isEmpty()) {
            // 如果沒有更多決策，默認選擇普通攻擊
            System.out.print("請輸入行動選擇 (數字): ");
            return new BasicAttack();
        }

        System.out.print("請輸入行動選擇 (可選：");
        for (int i = 0; i < availableActions.size(); i++) {
            System.out.print(i);
            if (i < availableActions.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("): ");
        String decision = decisions.poll();
        System.out.println(decision); // 顯示輸入的值
        int actionIndex = Integer.parseInt(decision.trim());

        if (actionIndex >= 0 && actionIndex < availableActions.size()) {
            return availableActions.get(actionIndex);
        }

        // 如果索引無效，返回普通攻擊
        return new BasicAttack();
    }

    @Override
    public List<Unit> chooseTargets(Unit unit, List<Unit> candidates, int needed) {
        List<Unit> selected = new ArrayList<>();

        if (candidates.isEmpty() || needed <= 0) {
            return selected;
        }

        if (decisions.isEmpty()) {
            // 如果沒有更多決策，選擇前面的目標
            System.out.print("請輸入目標選擇 (數字，用逗號分隔): ");
            for (int i = 0; i < Math.min(needed, candidates.size()); i++) {
                selected.add(candidates.get(i));
            }
            return selected;
        }

        System.out.print("請輸入目標選擇 (可選：");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.print(i);
            if (i < candidates.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("): ");
        String decision = decisions.poll();
        System.out.println(decision); // 顯示輸入的值
        String[] targetIndices = decision.split(",\\s*");

        for (int i = 0; i < Math.min(needed, targetIndices.length); i++) {
            try {
                int targetIndex = Integer.parseInt(targetIndices[i].trim());
                if (targetIndex >= 0 && targetIndex < candidates.size()) {
                    Unit target = candidates.get(targetIndex);
                    if (!selected.contains(target)) {
                        selected.add(target);
                    }
                }
            } catch (NumberFormatException e) {
                // 忽略無效的索引
            }
        }

        // 如果選擇的目標不足，補充前面的目標
        while (selected.size() < needed && selected.size() < candidates.size()) {
            for (Unit candidate : candidates) {
                if (!selected.contains(candidate)) {
                    selected.add(candidate);
                    break;
                }
            }
        }

        return selected;
    }
}
