package model.Building;

import model.Empire;

import java.util.HashMap;

public class PitchDitch extends Building{
    PitchDitch(Empire government) {
        super(government);
    }
    private Names names;

    public Names getNames() {
        return names;
    }
    public HashMap<String , Integer> cost = new HashMap<>();
    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood , int stone , int gold , int iron , int oil ){
        cost.put("wood" , wood);
        cost.put("stone" , stone);
        cost.put("gold" , gold);
        cost.put("iron" , iron);
        cost.put("oil" , oil);
    }
    //TODO oil per 5 square
    public void pitchDitch(){
        names = Names.PITCH_DITCH;
        createBuildingCost(0 , 0 , 0 , 0 , 2);
    }
}