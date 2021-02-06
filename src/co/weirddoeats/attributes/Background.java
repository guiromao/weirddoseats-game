package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    private Picture pic;
    private SimpleGfxGridPosition pos;
    private final int PADDING = 10;

    public Background(String pic){
        this.pic = new Picture(PADDING, PADDING, pic);
    }
    public void show(){
        pic.draw();
    }
    public void hide(){
        pic.delete();
    }
}
