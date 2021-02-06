package co.weirddoeats.game;

import co.weirddoeats.attributes.Music;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Main {


    public static void main(String[] args) {

        System.out.println("Yeah!");

        /*
        try{
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(classLoader.getResource("8bitretro.mp3"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
        }*/
        Game game = new Game();
        game.start();
    }

}
