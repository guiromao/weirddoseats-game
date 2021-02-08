package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;

public class VehicleFactory {

    private static final String [] vehicleTypes = {"biker", "biker", "motorbike", "car", "ferrari", "spaceshuttle"};
    private static final int [] speed = {1,2,3,4,8,12};
    private static final String [] pictures = {"motorcycle.png", "bike.png", "motorbike.png", "car.png", "ferrari.png", "spaceshuttle.png"};

    public static Vehicle generateVehicle(int level, GameGrid grid){
        return new Vehicle(vehicleTypes[level], speed[level], pictures[level], grid);
    }
}
