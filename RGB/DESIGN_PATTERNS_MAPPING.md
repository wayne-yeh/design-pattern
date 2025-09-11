# 🎯 RPG 專案設計模式對應表

## 📊 快速參考表

| #   | 設計模式                    | 解決的問題     | 核心接口/類別      | 代碼位置         |
| --- | --------------------------- | -------------- | ------------------ | ---------------- |
| 1   | **Strategy**                | 目標選擇變動性 | `TargetingPolicy`  | `rpg/targeting/` |
| 2   | **Strategy**                | 決策行為變動性 | `DecisionProvider` | `rpg/core/`      |
| 3   | **State**                   | 狀態行為變動性 | `State`            | `rpg/states/`    |
| 4   | **Command**                 | 技能執行變動性 | `Action`           | `rpg/skills/`    |
| 5   | **Observer**                | 死亡響應變動性 | `DeathListener`    | `rpg/death/`     |
| 6   | **Chain of Responsibility** | 條件處理變動性 | `OnePunchRule`     | `rpg/onepunch/`  |

---

## 🔍 詳細對應分析

### 1. Strategy Pattern - 目標選擇策略

```
Force: 不同技能有不同的目標選擇邏輯
├── OneEnemyPolicy      → 單一敵人 (水球、石化等)
├── AllEnemiesPolicy    → 所有敵人 (火球)
├── SelfPolicy          → 自己 (自我治療)
├── NAlliesPolicy       → N個友軍 (鼓舞)
└── AllUnitsPolicy      → 所有角色 (自爆)
```

### 2. Strategy Pattern - 決策策略

```
Force: 不同角色有不同的決策方式
├── HeroDecisionProvider    → 玩家輸入決策
├── AiDecisionProvider      → AI 算法決策
└── InteractiveHeroDP       → 即時互動決策
```

### 3. State Pattern - 角色狀態

```
Force: 角色狀態影響行為表現
├── NormalState         → 正常狀態 (可正常行動)
├── PetrochemicalState  → 石化狀態 (無法行動)
├── PoisonedState       → 中毒狀態 (每回合扣血)
└── CheerupState        → 鼓舞狀態 (傷害加成)
```

### 4. Command Pattern - 技能命令

```
Force: 統一封裝不同的技能執行邏輯
├── BasicAttack     → 普通攻擊
├── Waterball       → 水球攻擊
├── Fireball        → 火球攻擊
├── SelfHealing     → 自我治療
├── Petrochemical   → 石化技能
├── Poison          → 下毒技能
├── Summon          → 召喚技能
├── SelfExplosion   → 自爆技能
├── Cheerup         → 鼓舞技能
├── Curse           → 詛咒技能
└── OnePunch        → 一拳攻擊
```

### 5. Observer Pattern - 事件觀察

```
Force: 死亡事件需要觸發多種響應
├── DeathBus            → 事件發布者
├── CurseTracker        → 處理詛咒效果
└── SummonHealRule      → 處理召喚治癒
```

### 6. Chain of Responsibility - 責任鏈

```
Force: 一拳攻擊需要按條件優先序處理
├── HighHpRule      → HP ≥ 500 (300傷害)
├── BadStateRule    → 壞狀態 (3次80傷害)
├── CheerupRule     → 鼓舞狀態 (100傷害+移除狀態)
└── NormalRule      → 正常狀態 (100傷害)
```

---

## 🎯 模式應用原則驗證

### ✅ Strategy Pattern 驗證

- [x] 有一族相關算法 (目標選擇、決策邏輯)
- [x] 算法可以互相替換
- [x] 客戶端不需要知道具體算法
- [x] 易於擴展新算法

### ✅ State Pattern 驗證

- [x] 物件行為隨狀態改變
- [x] 狀態轉換邏輯明確
- [x] 避免大量條件判斷
- [x] 易於新增新狀態

### ✅ Command Pattern 驗證

- [x] 將請求封裝為物件
- [x] 支援請求的參數化
- [x] 調用者與接收者解耦
- [x] 易於擴展新命令

### ✅ Observer Pattern 驗證

- [x] 一對多依賴關係
- [x] 當一個物件改變時通知多個物件
- [x] 觀察者可以動態增減
- [x] 鬆耦合設計

### ✅ Chain of Responsibility 驗證

- [x] 多個物件可處理同一請求
- [x] 處理者鏈動態組織
- [x] 請求者不需知道具體處理者
- [x] 易於調整處理順序

---

## 🏗️ 設計原則遵循

### SOLID 原則檢查

| 原則    | 遵循情況 | 體現方式               |
| ------- | -------- | ---------------------- |
| **S**RP | ✅       | 每個類別職責單一       |
| **O**CP | ✅       | 對擴展開放，對修改封閉 |
| **L**SP | ✅       | 子類別可替換父類別     |
| **I**SP | ✅       | 接口小而專精           |
| **D**IP | ✅       | 依賴於抽象而非具體     |

### 其他設計原則

- ✅ **針對介面編程**：大量使用接口抽象
- ✅ **組合優於繼承**：通過組合實現彈性設計
- ✅ **封裝變化**：將變動的部分封裝起來
- ✅ **最少知識原則**：降低類別間依賴

---

## 🎖️ 設計成果

### 擴展性驗證

- 新增技能：只需實現 `Skill` 接口 ✅
- 新增狀態：只需實現 `State` 接口 ✅
- 新增 AI：只需實現 `DecisionProvider` 接口 ✅
- 新增事件響應：只需實現 `DeathListener` 接口 ✅

### 維護性提升

- 責任明確：每個類別職責單一 ✅
- 低耦合：通過接口交互 ✅
- 高內聚：相關功能集中管理 ✅
- 易測試：支援獨立單元測試 ✅

