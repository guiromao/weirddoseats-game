package co.weirddoeats.game;

import co.weirddoeats.attributes.GameObject;
import co.weirddoeats.attributes.Music;
import co.weirddoeats.gfx.simplegfx.GameGrid;
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

    private final int TIME_STAGE_SECONDS = 10;
    private final int NUMBER_STAGES = 3;

    private Keyboard keyboard;
    private Game game;
    private Text welcomeText;
    private Text pressSpace;
    private boolean isOnWelcome;
    private Text time;
    private Text score;
    private Text levelText;
    private int level;
    private int timeLeft;
    private int countStages;
    private Picture logo;

    public KeyboardController(Player player) {
        keyboard = new Keyboard(this);
        isOnWelcome = true;
        timeLeft = TIME_STAGE_SECONDS;
        level = 1;
        countStages = 1;
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
        logo.translate(160, -50);
        //logo.grow(-400, -400);
        logo.draw();

        welcomeText = new Text(360, 30 + GameGrid.INFO_BOARD_HEIGHT, "Welcome to");
        welcomeText.setColor(Color.BLACK);
        welcomeText.grow(120, 10);
        //welcomeText.draw();

        pressSpace = new Text(325, 265 + GameGrid.INFO_BOARD_HEIGHT, "Press <SPACE> to start the game! ;)");
        pressSpace.setColor(Color.BLACK);
        pressSpace.grow(120, 10);
        pressSpace.draw();

    }

    public void initAdditional() {
        score = new Text(90, 40, "Score: 0");
        score.setColor(Color.WHITE);
        score.grow(50, 20);
        score.draw();

        time = new Text(370, 40, "");
        time.setColor(Color.WHITE);
        time.grow(50, 20);
        time.setText("Time: " + timeLeft);
        time.draw();

        levelText = new Text(650, 40, "Level: " + level);
        levelText.setColor(Color.WHITE);
        levelText.grow(50, 20);
        levelText.draw();
    }

    public void updateInfo() {

        new Thread() {
            public void run() {
                try {

                    while(true){

                        while (timeLeft >= 0) {
                            if (time != null) {
                                time.delete();
                            }
                            time.delete();
                            Thread.sleep(500);
                            time = new Text(370, 40, "");
                            time.setColor(Color.WHITE);
                            time.grow(50, 20);
                            time.setText("Time: " + timeLeft);
                            time.draw();
                            Thread.sleep(500);
                            timeLeft--;
                        }
                        if (countStages % NUMBER_STAGES == 0) {
                            level++;
                            levelText.delete();
                            levelText = new Text(650, 40, "");
                            levelText.setColor(Color.WHITE);
                            levelText.grow(50, 20);
                            levelText.setText("Level: " + level);
                            levelText.draw();
                            game.getMusic().playNextSong();
                        }
                        timeLeft = TIME_STAGE_SECONDS;
                        countStages++;

                    }

                } catch (InterruptedException v) {
                    System.out.println(v);
                }

            }
        }.start();
    }

    public void updateScore(int scoreDone) {
        score.delete();
        score = new Text(100, 40, "");
        score.setColor(Color.WHITE);
        score.grow(50, 20);
        score.setText("Score: " + scoreDone);
        score.draw();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (isOnWelcome) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                game.setBoardColor(Color.BLACK);
                game.setInfoColor(Color.BLACK);
                logo.delete();
                welcomeText.delete();
                pressSpace.delete();
                playNextSong();
                isOnWelcome = false;
                initAdditional();
                game.getPlayer().getVehicle().getPosition().show();
                updateInfo();
                game.getGameObjects()[1].getPosition().show();
            }
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP || keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN ||
                keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT || keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            if (game.getPlayer()!= null) {
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
                    game.getPlayer().moveInDirection(direction);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void playNextSong() {
        game.getMusic().playNextSong();
    }

    public void setGame(Game g){
        game = g;
    }

}
