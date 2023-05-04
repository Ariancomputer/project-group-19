package controller.Building;

import model.Building.Building;
import model.Building.DrawBridge;
import model.Human.Troop.*;
import model.Empire;
import model.Map;
import view.Messages.SelectedBuildingMessages;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SelectedBuildingController {
    public static Empire empire;
    public static Building selectedBuilding;
    //TODO : add the tunneler troop to one of the buildings
    //TODO : all the troops made in here . their functions is not called in the functions soo after fixing the troops class it must be added
    //TODO REMOVE THE ARAB ARMY AND EUROPE ARMY
    public SelectedBuildingMessages gatehouse(Matcher matcherTaxRate) {
        int taxRate = Integer.parseInt(matcherTaxRate.group("taxRate"));
        if (taxRate > 7 | taxRate < -3) {
            empire.setTaxRateNumber(taxRate);
            return SelectedBuildingMessages.TAX_RATE_CHANGE_SUCCESSFUL;
        }
        return SelectedBuildingMessages.TAX_RATE_OUT_OF_BONDS;
    }

    public SelectedBuildingMessages drawBridge(Matcher matcherBridgeCondition) {
        String bridgeCondition = matcherBridgeCondition.group("bridgeCondition");
        if (bridgeCondition.equals("down")) {
            if (((DrawBridge) selectedBuilding).bridgeOpen) return SelectedBuildingMessages.BRIDGE_ALREADY_OPEN;
            ((DrawBridge) selectedBuilding).setBridgeOpen(true);
            int x = ((DrawBridge) selectedBuilding).getX();
            int y = ((DrawBridge) selectedBuilding).getY();
            Map.notPassable[x][y] = false;
            return SelectedBuildingMessages.BRIDGE_OPENED;
        } else {
            if (!((DrawBridge) selectedBuilding).bridgeOpen) return SelectedBuildingMessages.BRIDGE_ALREADY_CLOSE;
            ((DrawBridge) selectedBuilding).setBridgeOpen(true);
            int x = ((DrawBridge) selectedBuilding).getX();
            int y = ((DrawBridge) selectedBuilding).getY();
            Map.notPassable[x][y] = true;
            return SelectedBuildingMessages.BRIDGE_CLOSED;
        }
    }

    public void buyFromBarracks(Empire empire, int troopPrice, HashMap<String, Integer> allWeaponTools, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        empire.setLeatherArmourCount(empire.getLeatherArmourCount() - allWeaponTools.get("leatherArmour") * troopCount);
        empire.setMetalArmourCount(empire.getMetalArmourCount() - allWeaponTools.get("metalArmour") * troopCount);
        empire.setBowCount(empire.getBowCount() - allWeaponTools.get("bow") * troopCount);
        empire.setSwordCount(empire.getSwordCount() - allWeaponTools.get("sword") * troopCount);
        empire.setMaceCount(empire.getMaceCount() - allWeaponTools.get("mace") * troopCount);
        empire.setSpearCount(empire.getSpearCount() - allWeaponTools.get("spear") * troopCount);
        empire.setPeakCount(empire.getPeakCount() - allWeaponTools.get("peak") * troopCount);
        empire.setHorseCount(empire.getHorseCount() - allWeaponTools.get("horse") * troopCount);
        switch (troopName) {
            case "archer":
                empire.setEuropeArcherCount(empire.getEuropeArcherCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers archer = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(archer);
                }
            case "spearMan":
                empire.setSpearManCount(empire.getSpearManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Climbers spearMan = new Climbers(empire);
                    empire.empireArmy.add(spearMan);
                }
            case "maceMan":
                empire.setMaceManCount(empire.getMaceManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Climbers maceMan = new Climbers(empire);
                    empire.empireArmy.add(maceMan);
                }
            case "crossbowMan":
                empire.setCrossbowManCount(empire.getCrossbowManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers crossbowMan = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(crossbowMan);
                }
            case "pikeMan":
                empire.setPikeManCount(empire.getPikeManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Soldiers pikeMan = new Soldiers(empire);
                    empire.empireArmy.add(pikeMan);
                }
            case "swordMan":
                empire.setSwordManCount(empire.getSwordManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Soldiers swordMan = new Soldiers(empire);
                    empire.empireArmy.add(swordMan);
                }
            case "knight":
                empire.setKnightCount(empire.getKnightCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Soldiers knight = new Soldiers(empire);
                    empire.empireArmy.add(knight);
                }
        }
    }

    public SelectedBuildingMessages empireHasEnoughRecoursesToBuyTroopFromBarracks(Empire empire, HashMap<String, Integer> listOfTroopsBuyPrice,
                                                                                   HashMap<String, Integer> allWeaponTools, String troopName, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantCount = empire.getPeasantCount();
        if (empiresPeasantCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = listOfTroopsBuyPrice.get(troopName) * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        int requiredLeatherArmour = allWeaponTools.get("leatherArmour") * troopCount;
        int requiredMetalArmour = allWeaponTools.get("metalArmour") * troopCount;
        int requiredBow = allWeaponTools.get("bow") * troopCount;
        int requiredSword = allWeaponTools.get("sword") * troopCount;
        int requiredMace = allWeaponTools.get("mace") * troopCount;
        int requiredSpear = allWeaponTools.get("spear") * troopCount;
        int requiredPeak = allWeaponTools.get("peak") * troopCount;
        int requiredHorse = allWeaponTools.get("horse") * troopCount;
        if (!(requiredLeatherArmour < empire.getLeatherArmourCount() && requiredMetalArmour < empire.getMetalArmourCount() && requiredBow < empire.getBowCount() && requiredSword < empire.getSwordCount() && requiredMace < empire.getMaceCount() && requiredSpear < empire.getSpearCount() && requiredPeak < empire.getPeakCount() && requiredHorse < empire.getHorseCount())) {
            return SelectedBuildingMessages.NOT_ENOUGH_RESOURCES;
        }
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public SelectedBuildingMessages Barracks(Matcher matcherTroopName, Matcher matcherCount) {
        String troopName = matcherTroopName.group("troopName");
        int count = Integer.parseInt(matcherCount.group("count"));
        HashMap<String, Integer> listOfTroopsBuyPrice = new HashMap<>(); // good name and its buy price

        {
            listOfTroopsBuyPrice.put("archer", 8);
            listOfTroopsBuyPrice.put("spearMan", 8);
            listOfTroopsBuyPrice.put("maceMan", 20);
            listOfTroopsBuyPrice.put("crossbowMan", 20);
            listOfTroopsBuyPrice.put("pikeMan", 20);
            listOfTroopsBuyPrice.put("swordMan", 40);
            listOfTroopsBuyPrice.put("knight", 40);
        }
        HashMap<String, Integer> allWeaponTools = new HashMap<>();

        {
            allWeaponTools.put("leatherArmour", 0);
            allWeaponTools.put("metalArmour", 0);
            allWeaponTools.put("bow", 0);
            allWeaponTools.put("sword", 0);
            allWeaponTools.put("mace", 0);
            allWeaponTools.put("spear", 0);
            allWeaponTools.put("peak", 0);
            allWeaponTools.put("horse", 0);
        }
        switch (troopName) {
            case "archer":
                allWeaponTools.replace("bow", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("archer"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "spearMan":
                allWeaponTools.replace("spear", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("spearMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "maceMan":
                allWeaponTools.replace("mace", 1);
                allWeaponTools.replace("leatherArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("maceMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "crossbowMan":
                allWeaponTools.replace("bow", 1);
                allWeaponTools.replace("leatherArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("crossbowMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "pikeMan":
                allWeaponTools.replace("peak", 1);
                allWeaponTools.replace("metalArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("pikeMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "swordMan":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("swordMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "knight":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                allWeaponTools.replace("horse", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("knight"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
        }
        return null;

    }

    public SelectedBuildingMessages enoughResourcesToBuyFromMercenary(Empire empire, int troopPrice, String troopName, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantCount = empire.getPeasantCount();
        if (empiresPeasantCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromMercenary(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        switch (troopName) {
            case "arabianBow":
                empire.setArabianBowCount(empire.getArabianBowCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers arabianBow = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(arabianBow);
                }
            case "slave":
                empire.setSlaveCount(empire.getSlaveCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Soldiers slave = new Soldiers(empire);
                    empire.empireArmy.add(slave);
                }
            case "slinger":
                empire.setSlingerCount(empire.getSlingerCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers slinger = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(slinger);
                }
            case "assassin":
                empire.setAssassinCount(empire.getAssassinCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Climbers assassin = new Climbers(empire);
                    empire.empireArmy.add(assassin);
                }
            case "horseArcher":
                empire.setHorseArcherCount(empire.getHorseArcherCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers horseArcher = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(horseArcher);
                }
            case "arabianSwordMan":
                empire.setArabianSwordManCount(empire.getArabianSwordManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    Soldiers arabianSwordMan = new Soldiers(empire);
                    empire.empireArmy.add(arabianSwordMan);
                }
            case "fireThrower":
                empire.setFireThrowerCount(empire.getFireThrowerCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers fireThrower = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(fireThrower);
                }
        }
    }

    public SelectedBuildingMessages mercenary(Matcher matcherTroopName, Matcher matcherCount) {
        String troopName = matcherTroopName.group("troopName");
        int count = Integer.parseInt(matcherCount.group("count"));
        int empireGoldCount = empire.getGoldCount();
        HashMap<String, Integer> listOfTroopsBuyPrice = new HashMap<>(); // good name and its buy price

        {
            listOfTroopsBuyPrice.put("arabianBow", 75);
            listOfTroopsBuyPrice.put("slave", 5);
            listOfTroopsBuyPrice.put("slinger", 15);
            listOfTroopsBuyPrice.put("assassin", 60);
            listOfTroopsBuyPrice.put("horseArcher", 10);
            listOfTroopsBuyPrice.put("arabianSwordMan", 10);
            listOfTroopsBuyPrice.put("fireThrower", 100);
        }
        switch (troopName) {
            case "arabianBow":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), troopName, count);
                }
            case "slave":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), troopName, count);
                }
            case "slinger":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), troopName, count);
                }
            case "assassin":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), troopName, count);
                }
            case "horseArcher":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), troopName, count);
                }
            case "arabianSwordMan":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), troopName, count);
                }
            case "fireThrower":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), troopName, count);
                }
        }
        return null;
    }

    public SelectedBuildingMessages enoughResourcesToBuyFromEngineerGuild(Empire empire, int troopPrice, String troopName, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantCount = empire.getPeasantCount();
        if (empiresPeasantCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromEngineerGuild(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        switch (troopName) {
            case "engineer":
                empire.setEngineerCount(empire.getEngineerCount() + troopCount);
                empire.setEngineerCount(empire.getEngineerCount() + troopCount);
            case "ladderMan":
                for (int i = 0; i < troopCount; i++) {
                    Climbers ladderMan = new Climbers(empire);
                    empire.empireArmy.add(ladderMan);
                }
                empire.setLadderManCount(empire.getLadderManCount() + troopCount);
                empire.setLadderManCount(empire.getLadderManCount() + troopCount);
            case "tunneler":
                for (int i = 0; i < troopCount; i++) {
                    Tunneler tunneler = new Tunneler(empire);
                    empire.empireArmy.add(tunneler);
                }
                empire.setTunnelerCount(empire.getTunnelerCount() + troopCount);
                empire.setTunnelerCount(empire.getTunnelerCount() + troopCount);
        }
    }

    public SelectedBuildingMessages engineerGuild(Matcher matcherTroopName, Matcher matcherCount) {
        String troopName = matcherTroopName.group("troopName");
        int count = Integer.parseInt(matcherCount.group("count"));
        HashMap<String, Integer> engineerGuildTroopPrice = new HashMap<>();

        {
            engineerGuildTroopPrice.put("engineer", 30);
            engineerGuildTroopPrice.put("ladderMan", 5);
            engineerGuildTroopPrice.put("tunneler", 30);
        }
        switch (troopName) {
            case "engineer":
                if (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("arabianBow"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromEngineerGuild(empire, engineerGuildTroopPrice.get("arabianBow"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("arabianBow"), troopName, count));
                }
            case "ladderMan":
                if (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("slave"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromEngineerGuild(empire, engineerGuildTroopPrice.get("slave"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("slave"), troopName, count));
                }
            case "tunneler":
                if (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("slinger"), troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromEngineerGuild(empire, engineerGuildTroopPrice.get("slinger"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("slinger"), troopName, count));
                }
        }
        return null;
    }

    public void shop() {
        //TODO : pass this function to the shop menu
        // actually this function shouldn't exist  this is just a sign to know that we must pass it to the shop menu

    }

    //TODO : check the siegeTent algorithm later because i feel i have had a mistake in it but cant see it right now
    public SelectedBuildingMessages enoughResourcesToBuyFromSiegeTent(Empire empire, int troopPrice, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresEngineerCount = empire.getEngineerCount();
        if (empiresEngineerCount * 3 < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_ENGINEERS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromSiegeTent(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setEngineerCount(empire.getEngineerCount() - 3 * troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        switch (troopName) {
            case "catapult":
                empire.setCatapultCount(empire.getCatapultCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers catapult = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(catapult);
                }
            case "trebuchet":
                empire.setTrebuchetCount(empire.getTrebuchetCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers trebuchet = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(trebuchet);
                }
            case "siegeTower":
                empire.setSiegeTowerCount(empire.getSiegeTowerCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers siegeTower = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(siegeTower);
                }
            case "fireBalista":
                empire.setFireBalistaCount(empire.getFireBalistaCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers fireBalista = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(fireBalista);
                }
            case "batteringRam":
                empire.setBatteringRamCount(empire.getBatteringRamCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers batteringRam = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(batteringRam);
                }
            case "portableShield":
                empire.setPortableShieldCount(empire.getPortableShieldCount() + 3 * troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArchersAndThrowers portableShield = new ArchersAndThrowers(empire);
                    empire.empireArmy.add(portableShield);
                }
        }
    }

    public SelectedBuildingMessages siegeTent(Matcher matcherTroopName, Matcher matcherCount) {
        String siegeName = matcherTroopName.group("troopName");
        int count = Integer.parseInt(matcherCount.group("count"));

        HashMap<String, Integer> siegeTentTroopsPrice = new HashMap<>();

        {
            siegeTentTroopsPrice.put("catapult", 150);
            siegeTentTroopsPrice.put("trebuchet", 150);
            siegeTentTroopsPrice.put("siegeTower", 150);
            siegeTentTroopsPrice.put("fireBalista", 150);
            siegeTentTroopsPrice.put("batteringRam", 150);
            siegeTentTroopsPrice.put("portableShield", 5);
        }
        switch (siegeName) {
            case "catapult":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("catapult"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("catapult"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("catapult"), count));
                }
            case "trebuchet":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("trebuchet"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("trebuchet"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("trebuchet"), count));
                }
            case "siegeTower":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("siegeTower"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("siegeTower"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("siegeTower"), count));
                }
            case "fireBalista":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("fireBalista"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("fireBalista"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("fireBalista"), count));
                }
            case "batteringRam":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("batteringRam"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("batteringRam"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("batteringRam"), count));
                }
            case "portableShield":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("portableShield"), count / 3).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("portableShield"), siegeName, count / 3);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("portableShield"), count / 3));
                }
        }
        return null;
    }

    public SelectedBuildingMessages enoughResourcesToBuyFromChurch(Empire empire, int troopPrice, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantsCount = empire.getPeasantCount();
        if (empiresPeasantsCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromChurch(Empire empire, int troopPrice, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getEngineerCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        empire.setBlackMonkCount(empire.getBlackMonkCount() + troopCount);
        for (int i = 0; i < troopCount; i++) {
            Soldiers BlackMonk = new Soldiers(empire);
            empire.empireArmy.add(BlackMonk);
        }
    }

    public SelectedBuildingMessages church(Matcher matcherCount) {
        int count = Integer.parseInt(matcherCount.group("count"));
        HashMap<String, Integer> churchTroopsPrice = new HashMap<>();

        {
            churchTroopsPrice.put("blackMonk", 20);

        }
        if (enoughResourcesToBuyFromChurch(empire, churchTroopsPrice.get("blackMonk"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
            buyFromChurch(empire, churchTroopsPrice.get("blackMonk"), count);
            return SelectedBuildingMessages.PURCHASE_SUCCESS;
        } else {
            return (enoughResourcesToBuyFromChurch(empire, churchTroopsPrice.get("blackMonk"), count));
        }

    }


}
