package co.weirddoeats.game;

import co.weirddoeats.attributes.GameObject;
import co.weirddoeats.attributes.InfoColor;
import co.weirddoeats.attributes.Music;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.GridDirection;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class KeyboardController implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private Text welcomeText;
    private Text pressSpace;
    private boolean isOnWelcome;
    private Text time;
    private Text score;
    private Text levelText;
    private int timeLeft;
    private int countStages;
    boolean isGoal;
    private Picture logo;

    public KeyboardController(Game game) {
        keyboard = new Keyboard(this);
        this.game = game;
        isOnWelcome = true;
        timeLeft = game.getTime();
        countStages = 1;
        isGoal = false;
    }

    public void init() {

        KeyboardEvent welcomeEvent = new KeyboardEvent();
        welcomeEvent.setKey(KeyboardEvent.KEY_SPACE);
        welcomeEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(welcomeEvent);

        KeyboardEvent goUp = new KeyboardEvent();
        goUp.setKey(KeyboardEvent.KEY_UP);
        goUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goUp);

        KeyboardEvent goDown = new KeyboardEvent();
        goDown.setKey(KeyboardEvent.KEY_DOWN);
        goDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goDown);

        KeyboardEvent goLeft = new KeyboardEvent();
        goLeft.setKey(KeyboardEvent.KEY_LEFT);
        goLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goLeft);

        KeyboardEvent goRight = new KeyboardEvent();
        goRight.setKey(KeyboardEvent.KEY_RIGHT);
        goRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goRight);

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

    }

    public void initAdditional() {
        score = new Text(130, 45, "Score: 0");
        score.setColor(Color.WHITE);
        score.grow(50, 20);
        score.draw();

        time = new Text(510, 45, "");
        time.setColor(Color.WHITE);
        time.grow(50, 20);
        time.setText("Time: " + timeLeft);
        time.draw();

        levelText = new Text(900, 45, "Level: " + game.getLevel());
        levelText.setColor(Color.WHITE);
        levelText.grow(50, 20);
        levelText.draw();
    }

    public void updateInfo() {

        new Thread() {
            public void run() {
                try {

                    while (true) {

                        while (timeLeft >= 0) {
                            if (time != null) {
                                time.delete();
                            }
                            time.delete();
                            Thread.sleep(500);
                            time = new Text(510, 45, "");
                            time.setColor(Color.WHITE);
                            time.grow(50, 20);
                            time.setText("Time: " + timeLeft);
                            time.draw();
                            Thread.sleep(500);
                            timeLeft--;

                            if(timeLeft == 0){
                                gameOver();
                            }
                        }
                        if (countStages % Game.NUMBER_STAGES == 0) {

                        }
                        timeLeft = game.getTime();
                        countStages++;
                    }

                } catch (InterruptedException v) {
                    System.out.println(v);
                }

            }
        }.start();
    }

    private void gameOver(){

    }

    private void updateLevel() {
        levelText.delete();
        levelText = new Text(900, 45, "");
        levelText.setColor(Color.WHITE);
        levelText.grow(50, 20);
        levelText.setText("Level: " + game.getLevel());
        levelText.draw();
        game.getMusic().playNextSong();
    }

    public void updateScore(int scoreDone) {
        score.delete();
        score = new Text(130, 45, "");
        score.setColor(Color.WHITE);
        score.grow(50, 20);
        score.setText("Score: " + scoreDone);
        score.draw();
        game.getPlayer().setScore(scoreDone);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (isOnWelcome) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                game.setBoardColor(Color.WHITE);
                game.setInfoColor(InfoColor.values()[0].getColor());
                game.showBackground();
                logo.delete();
                welcomeText.delete();
                pressSpace.delete();
                playNextSong();
                isOnWelcome = false;
                initAdditional();
                game.getPlayer().getVehicle().getPosition().show();
                updateInfo();
                game.getGameObjects()[1].getPosition().show();
                game.getGameObjects()[3].getPosition().show();

            }
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP || keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN ||
                keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT || keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            if (game.getPlayer() != null) {
                if (true) {
                    GridDirection direction = null;
                    switch (keyboardEvent.getKey()) {
                        case KeyboardEvent.KEY_UP:
                            direction = GridDirection.UP;
                            break;
                        case KeyboardEvent.KEY_DOWN:
                            direction = GridDirection.DOWN;
                            break;
                        case KeyboardEvent.KEY_LEFT:
                            direction = GridDirection.LEFT;
                            break;
                        case KeyboardEvent.KEY_RIGHT:
                            direction = GridDirection.RIGHT;
                            break;
                    }
                    game.getPlayer().moveInDirection(direction, game, isGoal);
                    /*if(gotFood(game.getPlayer().getPosition()) && !isGoal){
                        System.out.println("Player found food!");
                        pickedFood();
                    }
                    else if(arrivedGoal(game.getPlayer().getPosition()) && isGoal){
                        didGoal();
<<<<<<< HEAD
                    }*/

                    //updateScore(scoreDone);

                        //updateScore(scoreDone);
                    }


            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void gameWin(){
        game.getGameObjects()[2].getPosition().hide();
        game.getGamePositions()[3].getPosition().hide();
        game.getGameObjects()[1].getPosition().hide();
        game.getGameObjects()[3].getPosition().hide();
    }

    public void didGoal() {

        int numberDelivered = game.getTimesDelivered() + 1;
        game.setTimesDelivered(numberDelivered);

        if (numberDelivered % Game.NUMBER_STAGES == 0) {
            game.upgradeLevel();
            updateLevel();
            game.getMusic().playSound("levelup.mp3");
        } else {
            game.getMusic().playSound("wincoin.mp3");

            game.getGameObjects()[2].getPosition().hide();
            game.getGamePositions()[3].getPosition().hide();
            game.generateNewCoordinates();
            game.getGameObjects()[1].getPosition().show();
            game.getGameObjects()[3].getPosition().show();
        }

        isGoal = false;

        updateScore(game.getPlayer().getScore() + (game.getLevel() * 100 + (50 * (Game.TIME_STAGE_SECONDS -
                (Game.TIME_STAGE_SECONDS - game.getTime())))));

        timeLeft = game.getInitialTime();

    }

    public void pickedFood() {
        game.getGameObjects()[1].getPosition().hide();
        game.getGameObjects()[3].getPosition().hide();
        game.getGameObjects()[2].getPosition().show();
        game.getGamePositions()[3].getPosition().show();
        game.getMusic().playSound("tap.mp3");
        isGoal = true;
    }

    public boolean arrivedGoal(SimpleGfxGridPosition pos) {
        return (pos.getRow() == game.getGamePositions()[3].getPosition().getRow() &&
                pos.getCol() == game.getGamePositions()[3].getPosition().getCol());
    }

    public boolean gotFood(SimpleGfxGridPosition pos) {
        return (pos.getRow() == game.getGamePositions()[2].getPosition().getRow() &&
                pos.getCol() == game.getGamePositions()[2].getPosition().getCol());
    }

    public void playNextSong() {
        game.getMusic().playNextSong();
    }

    public void setGame(Game g) {
        game = g;
    }


}
