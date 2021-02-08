package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Vehicle {

    private final int WIDTH = 15;
    private final int HEIGHT = 15;
    private String name;
    private Picture picture;
    private String pictureString;
    private int speed;
    private SimpleGfxGridPosition position;

    public Vehicle(String name, int speed, String picture, GameGrid grid){
        this.name = name;
        this.speed = speed;
        pictureString = picture;
        /*this.picture = new Picture(WIDTH, HEIGHT);
        this.picture.load(picture);*/
        position = grid.makeGridPosition(picture);

        //this.picture.translate(pos.getCol() * GameGrid.PIXELS, pos.getRow() * GameGrid.PIXELS);
    }

    public Picture getPicture(){
        return picture;
    }

    public String getPictureString(){
        return pictureString;
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

    public SimpleGfxGridPosition getPosition(){
        return position;
    }

    public int getSpeed(){
        return speed;
    }


    public void setPosition(SimpleGfxGridPosition pos){
        position = pos;
    }

    public int getHeight(){
        return HEIGHT;
    }
    public String getName(){
        return name;
    }

    public void setPicture(String picture){
        this.position.setPicture(picture);
    }

}
