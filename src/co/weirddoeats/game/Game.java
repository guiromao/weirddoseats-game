package co.weirddoeats.game;

import co.weirddoeats.attributes.*;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.GridColor;
import org.academiadecodigo.simplegraphics.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int TIME_STAGE_SECONDS = 10;
    public static final int NUMBER_STAGES = 3;

    private final int PADDING = 10;
    private final int WIDTH = 36;
    private final int HEIGHT = 16;

    private int level;
    private int ellapsedTime;
    private GameGrid gameGrid;
    private KeyboardController keyboard;
    private Background background;
    private Player player;
    private GameObject[] gameObjects;
    private Music music;
    private GamePosition[] positions;
    private Goal goal;
    private int timesDelivered;

    public Game() {
        gameGrid = new GameGrid(WIDTH, HEIGHT);
        this.player = new Player(VehicleFactory.generateVehicle(level, gameGrid));
        keyboard = new KeyboardController(this);
        level = 0;
        timesDelivered = 0;
        ellapsedTime = TIME_STAGE_SECONDS;
        music = new Music();
        positions = new GamePosition[4];
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

        Thread.sleep(1000);

        int time = 10;

//        if (time == 0){
//            game.gameOver();
//        }
//        if (timesDelivered == 9){
//            game.gameWin();
//        }



    }

    public void generateNewCoordinates() {
        for (int i = 1; i != 3; i++) {
            setNewCoordinates(gameObjects[i]);
            if (i == 1) {
                gameObjects[3] = FoodFactory.generateFood(level, (Building) gameObjects[1], gameGrid);
                gameObjects[1].getPosition().hide();
                positions[0] = new GamePosition("building", gameObjects[1].getPosition());
                positions[2] = new GamePosition("food", gameObjects[3].getPosition());
            }

            if (i == 2) {
                goal = new Goal((Building) gameObjects[2], gameGrid);
                positions[1] = new GamePosition("building", gameObjects[2].getPosition());
                positions[3] = new GamePosition("goal", goal.getPosition());
            }
        }
    }

    public void setNewCoordinates(GameObject object) {
        boolean hasPos = false;
        if (object instanceof Building) {
            while (!hasPos) {
                int count = 0;
                int row = generateRowRandom(2);
                int col = generateColRandom(2);

                if(row >= 1 && col>= 1 && row < (HEIGHT - 1) && col < (WIDTH - 4)){
                    String picture = ((Building) object).getImageName();
                    SimpleGfxGridPosition position = new SimpleGfxGridPosition(col, row, gameGrid, picture);

                    for (int i = 0; i != 3; i++) {
                        GameObject newObject = gameObjects[i];
                        if (newObject.getPosition() != null) {
                            System.out.println("Comparing our Pos " + col + ", " + row + " with position " +
                                    gameObjects[i].getPosition().getCol() + ", " + gameObjects[i].getPosition().getRow());
                            if (newObject.getPosition().getCol() != position.getCol()
                                    && newObject.getPosition().getRow() != position.getRow()) {
                                count++;
                            }
                        } else {
                            count++;
                        }
                    }
                    if (count == 3) {
                        hasPos = true;
                        ((Building) object).setPosition(position);
                        System.out.println("Generated positions Building. Col: " + object.getPosition().getCol() + ". Row: " + object.getPosition().getRow());
                    } else {
                        System.out.println("Did not get new coordinates for Building in loop. Looping again.");
                    }
                }
            }
        } else {
            System.out.println("This wasn\'t an instance of Building.");
        }
    }

    private int generateRow(){
        int initRow = getDenominatorRow();
        int realLevel = (level + 1) * 2;

        for(int i = realLevel; i != 6; i++){
            if(realLevel % i == 0 && initRow % i == 0){
                return i;
            }
        }
        return -1;
    }

    private int generateCol(){
        int initCol = getDenominatorCol();
        int realLevel = (level + 1) * 2;

        for(int i = realLevel; i != 6; i++){
            if(realLevel % i == 0 && initCol % i == 0){
                return i;
            }
        }
        return -1;
    }

    private int getDenominatorRow(){
        int rowPlayer = player.getVehicle().getPosition().getRow();
        int min = 2;

        for(int i = min; i != (HEIGHT - 1); i++){
            if(rowPlayer % i == 0){
                return i;
            }
        }
        return -1;
    }

    private int getDenominatorCol(){
        int colPlayer = player.getVehicle().getPosition().getRow();
        int min = 2;

        for(int i = min; i != (WIDTH - 1); i++){
            if(colPlayer % i == 0){
                return i;
            }
        }
        return -1;
    }

    public int generateRowRandom(int denominator) {
        List<Integer> result = new ArrayList<>();

        for (int i = denominator; i != (HEIGHT - 1); i++) {
            if (i % denominator == 0) {
                result.add(i);
            }
        }

        return result.get((int) Math.floor(Math.random() * result.size()));
    }

    public int generateColRandom(int denominator) {
        List<Integer> result = new ArrayList<>();

        for (int i = denominator; i != (WIDTH - 2); i++) {
            if ((i + 1) % denominator == 0) {
                result.add(i);
            }
        }

        return result.get((int) Math.floor(Math.random() * result.size()));
    }

    public void pickFood() {
        keyboard.pickedFood();
    }

    public void arrivedToGoal() {
        keyboard.didGoal();
    }

    public void upgradeLevel() {
        if((level + 1) <= 6){
            level++;
            InfoColor infoColor = InfoColor.values()[level];
            gameGrid.setInfoColor(infoColor.getColor());
            gameGrid.setBackground(BackgroundFactory.PICTURES[level]);

            getGameObjects()[2].getPosition().hide();
            getGamePositions()[3].getPosition().hide();
            generateNewCoordinates();
            getGameObjects()[1].getPosition().show();
            getGameObjects()[3].getPosition().show();
            player.levelUp(level);
            player.getVehicle().getPosition().hide();
            player.getVehicle().getPosition().show();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    public GamePosition[] getGamePositions() {
        return positions;
    }

    public Music getMusic() {
        return music;
    }

    public int getLevel() {
        return (level + 1);
    }

    public int getTime() {
        return ellapsedTime;
    }

    public int getTimesDelivered() {
        return timesDelivered;
    }

    public Goal getGoal() {
        return goal;
    }

    public void updateTime(){
        keyboard.updateInfo();
    }

    public void setBoardColor(Color color) {
        gameGrid.getGameBoard().setColor(color);
    }

    public void showBackground(){
        gameGrid.getBackground().draw();
    }

    public void setInfoColor(Color color) {
        gameGrid.getInfoBoard().setColor(color);
    }

    public void setTimesDelivered(int number) {
        timesDelivered = number;
    }

    public void setTime(int seconds) {
        ellapsedTime = seconds;
    }
}
