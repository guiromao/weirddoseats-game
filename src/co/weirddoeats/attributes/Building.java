package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.text.Position;

public class Building {

    private String name;
    private Picture picture;
    private SimpleGfxGridPosition pos;

    // Building Constructor
    public Building(String name,String picture,SimpleGfxGridPosition pos){
        this.name = name;
        this.picture = new Picture(10,10);
        this.picture.load(picture);
        this.pos = pos;
    }
    // Return building name
    public String getName(){
        return name;
    }
    // Set building name
    public void setName(String name){
        this.name = name;
    }
    // Return building picture
    public Picture getPicture(){
        return picture;
    }
    // Set building picture
    public void setPicture(Picture picture){
        this.picture = picture;
    }
    // Return building position
    public SimpleGfxGridPosition getPosition(){
        return pos;
    }
    // Set building position
    public void setPosition(SimpleGfxGridPosition pos){
        this.pos = pos;
    }
    // Show building
    public void show(){
        picture.draw();
    }
    // Hide building
    public void hide(){
        picture.delete();
    }

}
