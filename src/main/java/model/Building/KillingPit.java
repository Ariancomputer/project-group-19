package model.Building;

import model.Empire;

import java.util.HashMap;

public class KillingPit extends Building implements BuildingConstantFunctions {
    public KillingPit(Empire government) {
        super(government);
    }
    private boolean killingPitIsOn = true;

    public boolean isKillingPitIsOn() {
        return killingPitIsOn;
    }

    public void setKillingPitIsOn(boolean killingPitIsOn) {
        this.killingPitIsOn = killingPitIsOn;
    }

    private int damage;
    private Names name;
    private int hp ;
    private int maxHp;

    public Names getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public HashMap<String, Integer> cost = new HashMap<>();

    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }

    public HashMap<String, Integer> workersNeeded = new HashMap<>();

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);
    }


    public void killingPit() {
        hp = 900 ;
        maxHp = 900 ;
        damage = 700;
        name = Names.KILLING_PIT;
        createBuildingCost(6, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }


    @Override
    public int maxHp() {
        return 0;
    }

    @Override
    public int hp() {
        return 0;
    }

    @Override
    public String groundType() {
        return null;
    }

    @Override
    public String showBuildingName() {
        return name.getName();
    }
}
