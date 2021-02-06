package co.weirddoeats.game;

import co.weirddoeats.attributes.Music;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class MouseController implements MouseHandler {

    private Mouse mouse;
    private Ellipse circle;
    private Music music;

    public MouseController(Ellipse circle, Music music) {
        this.circle = circle;
        this.music = music;
        mouse = new Mouse(this);
    }


    public void init() {
        MouseEvent event = new MouseEvent(0, 0, MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        music.playNextSong();
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

}

