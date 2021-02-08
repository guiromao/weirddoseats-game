package co.weirddoeats.game;

import co.weirddoeats.attributes.Background;
import co.weirddoeats.attributes.BuildingFactory;
import co.weirddoeats.attributes.GameObject;
import co.weirddoeats.attributes.VehicleFactory;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.attributes.Building;
import co.weirddoeats.attributes.Music;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.GridColor;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Game {

    private final int PADDING = 10;
    private final int WIDTH = 35;
    private final int HEIGHT = 17;
    private int level;
    private int score;
    private GameGrid gameGrid;
    private KeyboardController keyboard;
    private Background background;
    private Player player;
    private GameObject [] gameObjects;
    private Music music;

    public Game(){
        gameGrid = new GameGrid(WIDTH, HEIGHT);
        this.player = new Player(VehicleFactory.generateVehicle(level, gameGrid));
        keyboard = new KeyboardController(player);
        level = 0;
        score = 0;
        music = new Music();
        gameObjects = new GameObject[3];
        gameObjects[0] = (GameObject) this.player;
        gameObjects[1] = BuildingFactory.generateBuilding(gameGrid, level);
        gameObjects[2] = BuildingFactory.generateBuilding(gameGrid, level);

    }

    public void start() throws InterruptedException {
        music.playNextSong();
        gameGrid.init();
        keyboard.init();

        generateNewCoordinates();
        keyboard.setGame(this);

        int time = 10;

    }

    public void generateNewCoordinates(){
        for(int i = 1; i != gameObjects.length; i++){
            setNewCoordinates(gameObjects[i]);
        }
    }

    public void setNewCoordinates(GameObject object){
        boolean hasPos = false;
        if(object instanceof Building){
            while(!hasPos){
                int count = 0;
                SimpleGfxGridPosition position = gameGrid.makeGridPosition(((Building) object).getImageName());
                for(int i = 0; i != gameObjects.length; i++){
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
                if(count == gameObjects.length){
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

    public Player getPlayer(){
        return player;
    }

    public GameObject [] getGameObjects(){
        return gameObjects;
    }

    public Music getMusic(){
        return music;
    }

    public void setBoardColor(Color color){
        gameGrid.getGameBoard().setColor(color);
    }

    public void setInfoColor(Color color){
        gameGrid.getInfoBoard().setColor(color);
    }

}
