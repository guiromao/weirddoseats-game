package co.weirddoeats.game;

import co.weirddoeats.attributes.Music;
<<<<<<< HEAD
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
=======
>>>>>>> master
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class MouseController implements MouseHandler {

    private Mouse mouse;
<<<<<<< HEAD
    private Ellipse circle;
=======
>>>>>>> master
    private Music music;

<<<<<<< HEAD
    public MouseController(Ellipse circle, Music music){
        this.circle = circle;
        this.music = music;
        mouse = new Mouse(this);
=======
    public MouseController(){
        mouse = new Mouse(this);
        music = new Music();
<<<<<<< HEAD
>>>>>>> master
        songIndex = 0;
=======
>>>>>>> backup
    }

    public void init(){
<<<<<<< HEAD
        MouseEvent event = new MouseEvent(0, 0, MouseEventType.MOUSE_CLICKED);
=======
>>>>>>> master
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        music.playNextSong();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
<<<<<<< HEAD
        music.stop();
=======
>>>>>>> master
        if(mouseEvent.getEventType() == MouseEventType.MOUSE_CLICKED){
            music.playNextSong();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

<<<<<<< HEAD
    public void playNewSong(){
<<<<<<< HEAD
        if(songIndex == tracks.length){
            songIndex = 0;
        }
        music = new Music(tracks[songIndex]);
        music.play();

        System.out.println("Going to play: " + music.getSongName());

        songIndex++;
    }

=======
        music.stop();
        if(songIndex == tracks.length){
            songIndex = 0;
        }

        music.play(tracks[songIndex]);
        songIndex++;
    }
>>>>>>> master
=======

>>>>>>> backup
}
