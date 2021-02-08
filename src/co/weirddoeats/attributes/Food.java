package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.text.Position;

public class Food extends GameObject{

    private String name;
    private Picture picture;
    private SimpleGfxGridPosition pos;

    // Food Constructor
    public Food(String name,String picture, SimpleGfxGridPosition pos, GameGrid grid){
        this.name = name;
        this.picture = new Picture(10,10);
        this.picture.load(picture);
        this.pos = grid.makeGridPosition(pos.getCol() + 1, pos.getRow(), picture);
    }

    public Food(){

    }

    // Return food name
    public String getName() {
        return name;
    }
    // Return food picture
    public Picture getPicture() {
        return picture;
    }
    // Return food position
    public SimpleGfxGridPosition getPosition() {
        return pos;
    }
    // Set food name
    public void setName(String name) {
        this.name = name;
    }
    // Set food picture
    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    // Set food position
    public void setPosition(SimpleGfxGridPosition pos) {
        this.pos = pos;
    }
    // Show food
    public void show(){
        picture.draw();
    }
    // Hide food
    public void hide(){
        picture.delete();
    }
}
