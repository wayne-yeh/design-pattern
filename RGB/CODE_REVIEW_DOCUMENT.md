# 🎯 RPG 對戰遊戲 - 設計模式應用分析文檔

## 📋 專案概述

本專案實現了一個完整的回合制 RPG 對戰系統，成功識別並解決了**多種行為變動性問題**，總共應用了 **6 個主要設計模式** 來達成高擴展性的架構設計。

---

## 🔍 Forces 分析與 Problem 識別

### 1. 目標選擇行為變動性 (Targeting Behavior Variation)

**Force 描述 (白話文)：**
不同的技能有不同的目標選擇方式 - 有些攻擊單一敵人、有些攻擊所有敵人、有些治療自己、有些支援友軍。每當我們要新增技能時，可能需要新的目標選擇邏輯，但我們不希望修改現有的技能代碼。

**Problem：** 如何讓目標選擇行為可以獨立變化，而不影響技能本身的實作？

**解決方案：** 套用 **Strategy Pattern (策略模式)**

- `TargetingPolicy` 接口定義目標選擇策略
- `OneEnemyPolicy`, `AllEnemiesPolicy`, `SelfPolicy`, `NAlliesPolicy` 等具體實現
- 每個技能組合不同的目標選擇策略

```java
// 技能只需要委派給策略
public class Waterball implements Skill {
    private TargetingPolicy targetingPolicy = new OneEnemyPolicy();
    // ...
}
```

---

### 2. 角色狀態型行為變動性 (State-based Behavior Variation)

**Force 描述 (白話文)：**
角色在不同狀態下會有不同的行為表現 - 正常狀態可以行動、石化狀態無法行動、中毒狀態每回合掉血、鼓舞狀態增加傷害。當角色狀態改變時，其行為也要跟著改變，而且我們希望能輕鬆擴充新的狀態效果。

**Problem：** 如何讓角色行為隨著狀態動態改變，並且新增狀態時不需修改現有代碼？

**解決方案：** 套用 **State Pattern (狀態模式)**

- `State` 接口定義狀態行為
- `NormalState`, `PetrochemicalState`, `PoisonedState`, `CheerupState` 具體實現
- 角色將行為委派給當前狀態

```java
public class Unit {
    private State state;

    public boolean canAct() {
        return isAlive() && state.canAct();
    }

    public void onTurnStart(Battle battle) {
        state.onTurnStart(this, battle);
    }
}
```

---

### 3. 技能執行行為變動性 (Skill Execution Variation)

**Force 描述 (白話文)：**
每個技能都有不同的執行效果 - 有些造成傷害、有些治療、有些改變狀態、有些召喚生物。我們希望能輕鬆新增新技能，而不需要修改戰鬥系統的核心邏輯。

**Problem：** 如何讓技能行為可以獨立擴充，並統一在戰鬥系統中調用？

**解決方案：** 套用 **Command Pattern (命令模式)**

- `Action` 接口封裝行動命令
- 每個技能實現 `execute()` 方法
- 戰鬥系統只需調用統一接口

```java
public interface Action {
    void execute(Unit caster, List<Unit> targets, Battle battle);
}

// 戰鬥系統中
action.execute(currentUnit, targets, this);
```

---

### 4. 決策行為變動性 (Decision Making Variation)

**Force 描述 (白話文)：**
不同的角色有不同的決策方式 - 英雄由玩家控制、AI 角色按照算法決策、可能還有其他類型的 AI。我們希望能輕鬆替換或新增不同的決策邏輯。

**Problem：** 如何讓決策行為可以插拔式替換，支援不同類型的決策者？

**解決方案：** 套用 **Strategy Pattern (策略模式)**

- `DecisionProvider` 接口定義決策策略
- `HeroDecisionProvider`, `AiDecisionProvider` 具體實現
- 角色可以動態設置決策提供者

```java
public class Unit {
    private DecisionProvider decisionProvider;

    // 可以動態更換決策策略
    public void setDecisionProvider(DecisionProvider dp) {
        this.decisionProvider = dp;
    }
}
```

---

### 5. 響應式行為變動性 (Reactive Behavior Variation)

**Force 描述 (白話文)：**
當角色死亡時，需要觸發多種不同的響應 - 詛咒者獲得 MP、召喚者獲得 HP、統計系統記錄數據等。這些響應彼此獨立，我們希望能動態新增或移除響應邏輯，而不影響死亡事件的觸發。

**Problem：** 如何讓事件響應行為可以動態組合，支援一對多的事件通知？

**解決方案：** 套用 **Observer Pattern (觀察者模式)**

- `DeathBus` 作為事件發布者
- `DeathListener` 接口定義觀察者
- `CurseTracker`, `SummonHealRule` 等具體觀察者

```java
public class Battle {
    private DeathBus deathBus;

    public Battle() {
        this.deathBus = new DeathBus();
        deathBus.subscribe(new CurseTracker());
        deathBus.subscribe(new SummonHealRule());
    }
}
```

---

### 6. 條件處理行為變動性 (Conditional Processing Variation)

**Force 描述 (白話文)：**
一拳攻擊技能根據目標的不同條件有不同的效果 - 高血量時造成 300 傷害、壞狀態時造成三次 80 傷害、鼓舞狀態時造成 100 傷害並移除狀態等。我們希望能輕鬆調整這些條件的優先順序或新增新的條件處理。

**Problem：** 如何讓條件處理邏輯可以靈活組合和擴充？

**解決方案：** 套用 **Chain of Responsibility (責任鏈模式)**

- `OnePunchRule` 接口定義處理規則
- `HighHpRule`, `BadStateRule`, `CheerupRule`, `NormalRule` 具體實現
- 按優先順序組成責任鏈

```java
public class OnePunch implements Skill {
    private List<OnePunchRule> rules = Arrays.asList(
        new HighHpRule(),      // 優先級 1
        new BadStateRule(),    // 優先級 2
        new CheerupRule(),     // 優先級 3
        new NormalRule()       // 優先級 4 (預設)
    );

    public void execute(Unit caster, List<Unit> targets, Battle battle) {
        for (OnePunchRule rule : rules) {
            if (rule.applies(target, battle)) {
                rule.apply(caster, target, battle);
                return; // 找到第一個適用的規則就停止
            }
        }
    }
}
```

---

## 🏗️ 架構設計優勢

### 1. **開閉原則 (OCP) 的實現**

- ✅ **新增技能**：實現 `Skill` 接口即可，無需修改現有代碼
- ✅ **新增狀態**：實現 `State` 接口即可，無需修改角色邏輯
- ✅ **新增 AI**：實現 `DecisionProvider` 接口即可
- ✅ **新增事件響應**：實現 `DeathListener` 接口並註冊即可

### 2. **低耦合高內聚**

- 技能、狀態、決策、目標選擇各自獨立
- 通過接口進行交互，降低依賴
- 每個類別職責單一且明確

### 3. **可測試性**

- 每個策略、狀態、命令都可以獨立測試
- 依賴注入使得 Mock 測試成為可能

---

## 📊 設計模式應用總結

| 設計模式                    | 應用場景 | 解決的 Force       | 核心接口           |
| --------------------------- | -------- | ------------------ | ------------------ |
| **Strategy**                | 目標選擇 | 目標選擇行為變動性 | `TargetingPolicy`  |
| **Strategy**                | AI 決策  | 決策行為變動性     | `DecisionProvider` |
| **State**                   | 角色狀態 | 狀態型行為變動性   | `State`            |
| **Command**                 | 技能系統 | 技能執行行為變動性 | `Action`           |
| **Observer**                | 死亡事件 | 響應式行為變動性   | `DeathListener`    |
| **Chain of Responsibility** | 一拳攻擊 | 條件處理行為變動性 | `OnePunchRule`     |

---

## 🎯 Code Review 重點

### ✅ 優點

1. **設計模式應用恰當**：每個模式都解決了明確的 Force
2. **擴展性優秀**：符合開閉原則，易於新增功能
3. **代碼結構清晰**：職責分離，易於理解和維護
4. **實現完整**：涵蓋了需求的所有功能點

### 🔄 可改進之處

1. **依賴注入**：可引入 DI 容器提升可測試性
2. **配置化**：部分硬編碼可提取為配置
3. **日誌系統**：可加入更完善的日誌記錄

---

## 🏆 總結

本專案成功識別並解決了 **6 種不同的行為變動性問題**，應用了 **6 個設計模式**，創建了一個高擴展性、低耦合的 RPG 對戰系統。每個設計決策都有明確的 Force 驅動，體現了優秀的軟體設計思維。

這個實現不僅滿足了當前需求，更重要的是為未來的擴展奠定了堅實的基礎，體現了「針對介面編程，而非實作」的設計原則。
