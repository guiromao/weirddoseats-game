package co.weirddoeats.game;

<<<<<<< HEAD
import co.weirddoeats.attributes.Music;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


=======
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

>>>>>>> master
public class Main {


    public static void main(String[] args) {
<<<<<<< HEAD
        System.out.println("Yeah!");

        Ellipse circle = new Ellipse(0,0, 100, 100);
        circle.setColor(Color.GREEN);
        circle.fill();

        MouseController mouse = new MouseController(circle, new Music("8bitretro.mp3"));
        mouse.init();


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
=======
        Game game = new Game();
        game.start();
>>>>>>> master
    }

}
