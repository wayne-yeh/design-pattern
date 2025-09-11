package rpg.units;

import rpg.core.DecisionProvider;
import rpg.skills.Action;
import rpg.states.State;
import rpg.states.NormalState;
import java.util.List;
import java.util.ArrayList;

public class Unit {
    private String name;
    private int maxHp;
    private int currentHp;
    private int maxMp;
    private int currentMp;
    private int strength;
    private State state;
    private List<Action> skills;
    private DecisionProvider decisionProvider;
    private int troopId; // 1 or 2

    public Unit(String name, int hp, int mp, int str, int troopId) {
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.maxMp = mp;
        this.currentMp = mp;
        this.strength = str;
        this.state = new NormalState();
        this.skills = new ArrayList<>();
        this.troopId = troopId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return "[" + troopId + "]" + name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int hp) {
        this.currentHp = Math.max(0, hp);
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getCurrentMp() {
        return currentMp;
    }

    public void setCurrentMp(int mp) {
        this.currentMp = Math.max(0, mp);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int str) {
        this.strength = str;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Action> getSkills() {
        return skills;
    }

    public void addSkill(Action skill) {
        this.skills.add(skill);
    }

    public DecisionProvider getDecisionProvider() {
        return decisionProvider;
    }

    public void setDecisionProvider(DecisionProvider dp) {
        this.decisionProvider = dp;
    }

    public int getTroopId() {
        return troopId;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public boolean isDead() {
        return currentHp <= 0;
    }

    public boolean canAct() {
        return isAlive() && state.canAct();
    }

    public boolean hasEnoughMp(int cost) {
        return currentMp >= cost;
    }

    public void consumeMp(int cost) {
        currentMp = Math.max(0, currentMp - cost);
    }

    public void heal(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
    }

    public void takeDamage(int amount) {
        currentHp = Math.max(0, currentHp - amount);
    }

    public void tickState() {
        if (state != null) {
            state.tickDown();
            if (state.ticksRemaining() <= 0) {
                state = new NormalState();
            }
        }
    }

    @Override
    public String toString() {
        return getDisplayName() + " (HP: " + currentHp + ", MP: " + currentMp +
                ", STR: " + strength + ", State: " + state.getName() + ")";
    }
}



