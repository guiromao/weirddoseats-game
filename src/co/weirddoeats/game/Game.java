package co.weirddoeats.game;

import co.weirddoeats.attributes.*;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.GridColor;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Game {

    private final int PADDING = 10;
    private final int WIDTH = 35;
    private final int HEIGHT = 17;
    private int level;
    private GameGrid gameGrid;
    private KeyboardController keyboard;
    private Background background;
    private Player player;
    private GameObject [] gameObjects;
    private Music music;
    private GamePosition [] positions;
    private Goal goal;
    private int timesDelivered;

    public Game(){
        gameGrid = new GameGrid(WIDTH, HEIGHT);
        this.player = new Player(VehicleFactory.generateVehicle(level, gameGrid));
        keyboard = new KeyboardController(this);
        level = 0;
        timesDelivered = 0;
        music = new Music();
        positions = new GamePosition[2];
        gameObjects = new GameObject[4];
        gameObjects[0] = (GameObject) this.player;
        gameObjects[1] = BuildingFactory.generateBuilding(gameGrid, level);
        gameObjects[2] = BuildingFactory.generateBuilding(gameGrid, level);
        //gameObjects[3] = new Food();

    }

    public void start() throws InterruptedException {
        music.playNextSong();
        gameGrid.init();
        keyboard.init();

        generateNewCoordinates();

        int time = 10;

    }

    public void generateNewCoordinates(){
        for(int i = 1; i != 3; i++){
            setNewCoordinates(gameObjects[i]);
            if(i == 1){
                gameObjects[3] = FoodFactory.generateFood(level, (Building) gameObjects[1], gameGrid);
                gameObjects[1].getPosition().hide();
                positions[0] = new GamePosition("food", gameObjects[3].getPosition());
            }

            if(i == 2){
                goal = new Goal((Building) gameObjects[2], gameGrid);
                positions[1] = new GamePosition("goal", goal.getPosition());
            }
        }
    }

    public void setNewCoordinates(GameObject object){
        boolean hasPos = false;
        if(object instanceof Building){
            while(!hasPos){
                int count = 0;
                SimpleGfxGridPosition position = gameGrid.makeGridPosition(((Building) object).getImageName());
                for(int i = 0; i != 3; i++){
                    GameObject newObject = gameObjects[i];
                    if(newObject.getPosition() != null){
                        if(newObject.getPosition().getCol() != position.getCol()
                                && newObject.getPosition().getRow() != position.getRow()){
                            count++;
                        }
                    }
                    else {
                        count++;
                    }
                }
                if(count == 2){
                    hasPos = true;
                    ((Building) object).setPosition(position);
                    System.out.println("Generated positions Building. Col: " + object.getPosition().getCol() + ". Row: " + object.getPosition().getRow());
                }
                else {
                    System.out.println("Did not get new coordinates for Building in loop. Looping again.");
                }
            }
        }
        else {
            System.out.println("This wasn\'t an instance of Building.");
        }
    }

    public void upgradeLevel() {
        level++;
        player.levelUp(level);
    }

    public Player getPlayer(){
        return player;
    }

    public GameObject [] getGameObjects(){
        return gameObjects;
    }

    public GamePosition [] getGamePositions(){
        return positions;
    }

    public Music getMusic(){
        return music;
    }

    public int getLevel(){
        return (level + 1);
    }

    public int getTimesDelivered(){
        return timesDelivered;
    }

    public Goal getGoal(){
        return goal;
    }

    public void setBoardColor(Color color){
        gameGrid.getGameBoard().setColor(color);
    }

    public void setInfoColor(Color color){
        gameGrid.getInfoBoard().setColor(color);
    }

    public void setTimesDelivered(int number){
        timesDelivered = number;
    }
}
