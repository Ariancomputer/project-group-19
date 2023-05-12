import controller.GameController;
import controller.JsonController;
import controller.PathFindingController;
import model.*;
import model.Building.*;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;
import model.Obstacle.ObstacleName;
import model.Obstacle.WaterSources;
import view.BuildingMenu;
import view.GameMenu;
import view.LoginMenu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static java.lang.Math.floor;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user6", "aa", "dorsa", "a", "1", "1", 1);
        Empire empire = new Empire();
        Empire empire1 = new Empire();
        Manage.setCurrentEmpire(empire);
        Map.CreateMap(200);
        Map.mapSize = 200;
        empire.setUser(newUser);
        empire1.setUser(newUser1);
        empire.setEngineerCount(60000000);
        empire.setWorkerCount(500000);
        empire.setWoodCount(20000000);
        empire.setStoneCount(300000);
        empire.setGoldCount(1000000);
        empire.setOilAmount(1000000);
        empire.setIronCount(10000000);
        empire.setEuropeArcherCount(4);



        ArchersAndThrowers archerEnemy = new ArchersAndThrowers(empire1);
        archerEnemy.archer(3,1);
        System.out.println("Army :"+ archerEnemy+ " owner: "+archerEnemy.getOwner().getName());
        ArchersAndThrowers archerEnemy2 = new ArchersAndThrowers(empire1);
        archerEnemy2.archer(2,4);
        ArchersAndThrowers archerEnemy3 = new ArchersAndThrowers(empire1);
        archerEnemy3.archer(2,3);
        ArchersAndThrowers archerEnemy4 = new ArchersAndThrowers(empire1);
        archerEnemy4.archer(2,4);
        CagedWarDogs cagedWarDogs =  new CagedWarDogs(empire);
        cagedWarDogs.cagedWarDogs();
        empire.cagedWarDogsCoordinate.add(0,2*Map.mapSize+5);
        ArchersAndThrowers archers1 = new ArchersAndThrowers(empire);
        archers1.archer(0,0);
        System.out.println("Army : "+archers1+" owner: "+archers1.getOwner().getName());
        ArchersAndThrowers archers2 = new ArchersAndThrowers(empire);
        archers2.archer(0,0);
        System.out.println("Army : "+archers2+" owner: "+archers2.getOwner().getName());
        ArchersAndThrowers archers3 = new ArchersAndThrowers(empire);
        archers3.archer(0,0);
        System.out.println("Army : "+archers3+" owner: "+archers3.getOwner().getName());
        ArchersAndThrowers archers4 = new ArchersAndThrowers(empire);
        archers4.archer(2,3);
        System.out.println("Army : "+archers4+" owner: "+archers4.getOwner().getName());
        ArchersAndThrowers archers5 = new ArchersAndThrowers(empire);
        archers5.trebuchet(2,3);




        Map.getTroopMap()[2][0].add(archers1);
        Map.getTroopMap()[2][0].add(archers2);
        Map.getTroopMap()[3][1].add(archers3);
        Map.getTroopMap()[3][1].add(archers4);
        Map.getTroopMap()[3][1].add(archers5);
        empire.empireArmy.add(archers1);
        empire.empireArmy.add(archers2);
        empire.empireArmy.add(archers3);
        empire.empireArmy.add(archers4);
        empire.empireArmy.add(archers5);
        Map.notPassable[2][3] = false;
        Tower building = new Tower(empire1);
        building.lookoutTower();
        PitchDitch pitchDitch = new PitchDitch(empire);
        pitchDitch.pitchDitch();
        Map.getBuildingMap()[2][1].add(0,pitchDitch);
        //Map.getBuildingMap()[4][5].add(0,building);
        GameMenu gameMenu = new GameMenu();
        gameMenu.run(scanner);
    }
}