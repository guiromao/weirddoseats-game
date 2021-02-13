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

    private boolean isOnWelcome;
    private boolean isGoal;
    private boolean gameEnded;


    public KeyboardController(Game game) {
        keyboard = new Keyboard(this);
        this.game = game;
        isOnWelcome = true;
        isGoal = false;
        gameEnded = false;
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
    }


    public void startInfo(){
        game.startInfo();
    }

    public void startGame(){
        game.startGame();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (isOnWelcome) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                if(!gameEnded){
                    startGame();
                    playNextSong();
                }
                else {
                    game.reload();
                    gameEnded = false;
                    game.increaseAttempts();
                }
                isOnWelcome = false;
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

                }

            }
        }
    }

    public void gameOver(){
        isOnWelcome = true;
        gameEnded = true;
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void didGoal() {
        isGoal = false;
    }

    public void pickedFood() {
        isGoal = true;
    }

    public void playNextSong() {
        game.getMusic().playNextSong();
    }

    public void setGame(Game g) {
        game = g;
    }


}
