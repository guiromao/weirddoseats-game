package co.weirddoeats.game;

import co.weirddoeats.attributes.*;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.GridColor;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int TIME_STAGE_SECONDS = 5;
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
    private Text welcomeText;
    private Text pressSpace;
    private Text time;
    private Text score;
    private Text levelText;
    private Text gameOverText;
    private Text scoreText;
    private Picture logo;
    private int countStages;
    private int timeLeft;
    private FileManager fileManager;
    private boolean hasWon;
    private int attempts;
    private int highestScore;

    public Game() {
        gameGrid = new GameGrid(WIDTH, HEIGHT);
        load();
        keyboard = new KeyboardController(this);
        attempts = 1;

    }

    public void load(){
        level = 0;
        this.player = new Player(VehicleFactory.generateVehicle(level, gameGrid));
        timesDelivered = 0;
        countStages = 0;
        ellapsedTime = TIME_STAGE_SECONDS + 1;
        music = new Music();
        positions = new GamePosition[4];
        gameObjects = new GameObject[4];
        gameObjects[0] = (GameObject) this.player;
        gameObjects[1] = BuildingFactory.generateBuilding(gameGrid, level);
        gameObjects[2] = BuildingFactory.generateBuilding(gameGrid, level);
        hasWon = false;
    }

    public void initAdditional() {
        if(logo != null && welcomeText != null && pressSpace != null && scoreText != null){
            logo.delete();
            welcomeText.delete();
            pressSpace.delete();
            scoreText.delete();
        }

        logo = new Picture();
        logo.load("logo.png");
        logo.translate(320, -50);
        //logo.grow(-400, -400);
        logo.draw();

        welcomeText = new Text(330, 30 + GameGrid.INFO_BOARD_HEIGHT, "Welcome to");
        welcomeText.setColor(Color.BLACK);
        welcomeText.grow(120, 10);
        //welcomeText.draw();

        pressSpace = new Text(485, 265 + GameGrid.INFO_BOARD_HEIGHT, "Press <SPACE> to start the game! ;)");
        pressSpace.setColor(Color.BLACK);
        pressSpace.grow(120, 10);
        pressSpace.draw();

        scoreText = new Text(GameGrid.PADDING * 10, 450  + GameGrid.INFO_BOARD_HEIGHT, ("Highscore: " + highestScore));
        scoreText.setColor(Color.GREEN);
        scoreText.grow(70, 25);
        scoreText.draw();

    }

    private void initTexts(){
        score = new Text(130, 45, "Score: 0");
        score.setColor(Color.WHITE);
        score.grow(50, 20);
        score.draw();

        if(time != null){
            time.delete();
        }
        time = new Text(510, 45, "");
        time.setColor(Color.WHITE);
        time.grow(50, 20);
        time.setText("Time: " + timeLeft);
        time.draw();

        levelText = new Text(900, 45, "Level: " + getLevel());
        levelText.setColor(Color.WHITE);
        levelText.grow(50, 20);
        levelText.draw();
    }

    public void updateLevel() {
        levelText.delete();
        levelText = new Text(900, 45, "");
        levelText.setColor(Color.WHITE);
        levelText.grow(50, 20);
        levelText.setText("Level: " + getLevel());
        levelText.draw();
        music.playNextSong();
    }

    public void updateScore(int scoreDone) {
        score.delete();
        score = new Text(130, 45, "");
        score.setColor(Color.WHITE);
        score.grow(50, 20);
        score.setText("Score: " + scoreDone);
        score.draw();
        player.setScore(scoreDone);
    }

    public void updateInfo() {

        new Thread() {
            public void run() {
                try {
                    boolean gameEnded = false;

                    while (!gameEnded) {

                        while (timeLeft >= 0) {
                            if (time != null) {
                                time.delete();
                            }
                            time.delete();
                            Thread.sleep(500);
                            time.delete();
                            time = new Text(510, 45, "");
                            time.setColor(Color.WHITE);
                            time.grow(50, 20);
                            time.setText("Time: " + (timeLeft - 1));
                            if((timeLeft - 1) >= 0){
                                time.draw();
                            }
                            else {
                                time.delete();
                            }
                            Thread.sleep(500);
                            timeLeft--;

                            if(timeLeft == 0){
                                time.delete();
                                gameEnded = true;
                                if(!hasWon){
                                    gameOver();
                                }
                            }
                        }
                        if (countStages % Game.NUMBER_STAGES == 0) {

                        }
                        timeLeft = getTime();
                        countStages++;
                    }
                    time.delete();

                } catch (InterruptedException v) {
                    System.out.println(v);
                }

            }
        }.start();
    }

    private void gameOver(){

        if(gameObjects != null){
            for(GameObject o: gameObjects){
                o.getPosition().hide();
            }
        }

        music.stop();
        music.playSound("gameover.mp3");
        levelText.delete();
        score.delete();
        time.setText("");
        time.delete();
        gameGrid.setInfoColor(Color.BLACK);
        gameGrid.setBackgroundColor(Color.BLACK);
        gameGrid.setBackground("black.png");
        gameOverText = new Text(530, 200, "Game Over :/");
        gameOverText.grow(200,70);
        gameOverText.setColor(Color.WHITE);
        gameOverText.draw();
        pressSpace.delete();
        pressSpace = new Text(515, 265 + GameGrid.INFO_BOARD_HEIGHT, "");
        pressSpace.setText("Press <SPACE> to continue");
        pressSpace.grow(120, 10);
        pressSpace.setColor(Color.WHITE);
        pressSpace.draw();
        keyboard.gameOver();
        writeScore(Color.WHITE);
    }

    public void writeScore(Color color){
        String textScore = "";
        if(checkScore()){
            fileManager.writeScore(player.getScore());
            textScore += "New High Score: ";
        }
        else {
            textScore += "Your score: ";
        }

        if(scoreText != null){
            scoreText.delete();
        }

        textScore += player.getScore() + "!";
        scoreText = new Text(GameGrid.PADDING * 10, 570, textScore);
        scoreText.grow(70, 25);
        scoreText.setColor(color);
        scoreText.draw();
    }

    public boolean checkScore(){
        return(player.getScore() > highestScore);
    }

    private void readScore(){
        fileManager = new FileManager();
        highestScore = fileManager.readScore();
    }

    private void gameWin(){
        if(gameObjects != null){
            for(GameObject o: gameObjects){
                o.getPosition().hide();
            }
        }

        ((Player)gameObjects[0]).hide();

        hasWon = true;
        music.stop();
        music.playSound("8bitwin.mp3");
        getGameObjects()[2].getPosition().hide();
        getGamePositions()[3].getPosition().hide();
        getGameObjects()[1].getPosition().hide();
        getGameObjects()[3].getPosition().hide();

        player.getVehicle().getPosition().hide();
        levelText.delete();
        score.delete();
        time.setText("");
        time.delete();
        gameGrid.setInfoColor(Color.WHITE);
        gameGrid.setBackgroundColor(Color.WHITE);
        gameGrid.getBackground().delete();
        if(gameOverText != null){
            gameOverText.delete();
        }
        gameOverText = new Text(500, 200, "You completed all the deliveries!!!");
        gameOverText.grow(400,70);
        gameOverText.setColor(Color.BLACK);
        gameOverText.draw();
        pressSpace.delete();
        pressSpace = new Text(515, 265 + GameGrid.INFO_BOARD_HEIGHT, "");
        pressSpace.setText("Press <SPACE> to continue");
        pressSpace.grow(120, 10);
        pressSpace.setColor(Color.BLACK);
        pressSpace.draw();
        keyboard.gameOver();
        writeScore(Color.BLACK);
    }

    public void startInfo(){
        logo.delete();
        welcomeText.delete();
        pressSpace.delete();
        scoreText.delete();
        updateInfo();
    }

    public void restart(){
        music.stop();
        pressSpace.delete();
        gameOverText.delete();
        load();
        initAdditional();
        gameGrid.setBackgroundColor(Color.WHITE);
        gameGrid.setInfoColor(Color.WHITE);
        updateInfo();
        initTexts();

    }

    public void reload(){
        gameOverText.delete();
        time.delete();
        score.delete();
        levelText.delete();
        gameGrid = new GameGrid(WIDTH, HEIGHT);
        load();
        keyboard = new KeyboardController(this);
        try{
            start();
        }
        catch (InterruptedException ew){
            ew.printStackTrace();
        }

    }

    public void start() throws InterruptedException {
        music.playNextSong();
        readScore();
        gameGrid.init();
        keyboard.init();
        initAdditional();
        initTexts();
        time.delete();

        generateNewCoordinates();

    }

    public void generateNewCoordinates() {
        for (int i = 1; i != 3 && !hasWon; i++) {
            setNewCoordinates(gameObjects[i]);
            if (i == 1) {
                gameObjects[3] = FoodFactory.generateFood(level, (Building) gameObjects[1], gameGrid);
                gameObjects[1].getPosition().hide();
                positions[0] = new GamePosition("building", gameObjects[1].getPosition());
                positions[2] = new GamePosition("food", gameObjects[3].getPosition());
            }

            if (i == 2) {
                goal = new Goal((Building) gameObjects[2], gameGrid, level);
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

                    for (int i = 0; i != 3 && !hasWon; i++) {
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
                        ((Building) object).getPosition().hide();
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

    public int getInitialTime(){
        return (TIME_STAGE_SECONDS - (level) + 1);
    }

    public void arrivedToGoal() {
        if(!hasWon){
            int numberDelivered = getTimesDelivered() + 1;
            setTimesDelivered(numberDelivered);

            if (numberDelivered % Game.NUMBER_STAGES == 0) {
                upgradeLevel();
                updateLevel();
                getMusic().playSound("levelup.mp3");
            } else {
                getMusic().playSound("wincoin.mp3");

                getGameObjects()[2].getPosition().hide();
                getGamePositions()[3].getPosition().hide();
                generateNewCoordinates();
                getGameObjects()[1].getPosition().hide();
                getGameObjects()[3].getPosition().show();
            }

            updateScore(getPlayer().getScore() + (getLevel() * 100 + (50 * timeLeft)));

            timeLeft = getInitialTime();

            keyboard.didGoal();
        }
    }

    public void upgradeLevel() {
        if((level + 1) < 6){
            level++;
            InfoColor infoColor = InfoColor.values()[level];
            gameGrid.setInfoColor(infoColor.getColor());
            gameGrid.setBackground(BackgroundFactory.PICTURES[level]);

            getGameObjects()[2].getPosition().hide();
            getGamePositions()[3].getPosition().hide();
            generateNewCoordinates();
            getGameObjects()[1].getPosition().hide();
            getGameObjects()[3].getPosition().show();
            player.levelUp(level);
            player.getVehicle().getPosition().hide();
            player.getVehicle().getPosition().show();
        }

        else if(timesDelivered == NUMBER_STAGES * (level + 1)){
            gameWin();
        }
    }

    public void pickFood(){
        if(!hasWon){
            gameObjects[1].getPosition().hide();
            gameObjects[3].getPosition().hide();
            gameObjects[2].getPosition().hide();
            positions[3].getPosition().show();
            music.playSound("tap.mp3");
            keyboard.pickedFood();
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

    public int getAttempts(){
        return attempts;
    }

    public Goal getGoal() {
        return goal;
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

    public void increaseAttempts(){
        attempts++;
    }

    public void startGame() {
        setBoardColor(Color.WHITE);
        setInfoColor(InfoColor.values()[0].getColor());
        showBackground();
        startInfo();
        getPlayer().getVehicle().getPosition().show();
        getGameObjects()[1].getPosition().hide();
        getGameObjects()[3].getPosition().show();
    }
}
