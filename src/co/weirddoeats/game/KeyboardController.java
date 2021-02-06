package co.weirddoeats.game;

import co.weirddoeats.attributes.Music;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardController implements KeyboardHandler {

    private Keyboard keyboard;
    private Text welcomeText;
    private Music music;
    private boolean isOnWelcome;

    public KeyboardController(){
        keyboard = new Keyboard(this);
        music = new Music();
        isOnWelcome = true;
        welcomeText = new Text(100, 100, "Welcome to WeirDDOS Eats!\nPress <Enter> to start the game!");
        welcomeText.setText("Welcome to WeirDDOS Eats!\nPress <Enter> to start the game!");
        welcomeText.setColor(Color.WHITE);
        welcomeText.draw();
    }

    public void init(){
        music.playNextSong();

        KeyboardEvent welcomeEvent = new KeyboardEvent();
        welcomeEvent.setKey(KeyboardEvent.KEY_SPACE);
        welcomeEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(welcomeEvent);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(isOnWelcome){
            if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE){
                welcomeText.delete();
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
