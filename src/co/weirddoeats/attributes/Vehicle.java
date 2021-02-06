package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Vehicle {

    private final int WIDTH = 15;
    private final int HEIGHT = 15;
    private Picture picture;

    public Vehicle(String picture){
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
