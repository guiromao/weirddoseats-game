package co.weirddoeats.game;

import co.weirddoeats.attributes.GameObject;
import co.weirddoeats.attributes.Vehicle;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import co.weirddoeats.grid.GridDirection;

public class Player extends GameObject {

    private Vehicle vehicle;
    private int score;
    private boolean isShown;

    public Player(Vehicle vehicle){
        this.vehicle = vehicle;
        this.score = 0;
        isShown = false;
    }

    public void show(){
        vehicle.getPosition().show();
        isShown = true;
    }

    public void hide(){
        vehicle.getPosition().hide();
        isShown = false;
    }

    public void moveInDirection(GridDirection direction){
        vehicle.getPosition().moveInDirection(direction, vehicle.getSpeed());
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public boolean isShown(){
        return isShown;
    }

    public void setVehicle(Vehicle newVehicle){
        vehicle = newVehicle;
    }

    @Override
    public SimpleGfxGridPosition getPosition() {
        return vehicle.getPosition();
    }

    public void setScore(int score){
        this.score = score;
    }
}
