package rpg.battle;

import rpg.units.Unit;
import java.util.List;
import java.util.ArrayList;

public class Troop {
    private List<Unit> units;
    private int id; // 1 or 2

    public Troop(int id) {
        this.id = id;
        this.units = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public List<Unit> getUnits() {
        return units;
    }

    public List<Unit> getAliveUnits() {
        return units.stream()
                .filter(Unit::isAlive)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public boolean isAnnihilated() {
        return getAliveUnits().isEmpty();
    }

    public int getId() {
        return id;
    }

    public Unit getHero() {
        if (id == 1 && !units.isEmpty()) {
            return units.get(0);
        }
        return null;
    }

    public int size() {
        return units.size();
    }
}
