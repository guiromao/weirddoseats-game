package co.weirddoeats.game;

import co.weirddoeats.attributes.Music;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardController implements KeyboardHandler {

    private Keyboard keyboard;
    private Text welcomeText;
    private Text pressSpace;
    private Music music;
    private boolean isOnWelcome;

    public KeyboardController(){
        keyboard = new Keyboard(this);
        music = new Music();
        isOnWelcome = true;

    }

    public void init(){
        music.playNextSong();

        KeyboardEvent welcomeEvent = new KeyboardEvent();
        welcomeEvent.setKey(KeyboardEvent.KEY_SPACE);
        welcomeEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(welcomeEvent);

        welcomeText = new Text(200, 100, "Welcome to WeirDDOS Eats!");
        welcomeText.setColor(Color.WHITE);
        //welcomeText.grow(100, 100);
        welcomeText.draw();

        pressSpace = new Text(200, 140, "Press <Enter> to start the game! ;)");
        pressSpace.setColor(Color.WHITE);
        //welcomeText.grow(100, 100);
        pressSpace.draw();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(isOnWelcome){
            if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE){
                welcomeText.delete();
                pressSpace.delete();
                music.playNextSong();
                isOnWelcome = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public Music getMusic(){
        return music;
    }

}
