package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    private final int PADDING = 10;
    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    private Picture picture;
    private SimpleGfxGridPosition pos;

    public Background(String picture){
        this.picture = new Picture(WIDTH, HEIGHT);
        this.picture.load(picture);
    }
    public void show(){
        picture.draw();
    }
    public void hide(){
        picture.delete();
    }
    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }
}
