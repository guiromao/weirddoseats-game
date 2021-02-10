package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;

public class VehicleFactory {


    private static final String [] vehicleTypes = {"bicycle", "biker", "spaceship"};
    private static final int [] speed = {1,2,3,4,8,12};
    private static final String [] pictures = {"bicycle_up.png", "bike.png", "motorbike.png", "car.png", "ferrari.png", "spaceshuttle.png"};


    public static Vehicle generateVehicle(int level, GameGrid grid){
        return new Vehicle(vehicleTypes[level], speed[level], pictures[level], grid);
    }

    public static String getVehicleTypes(int level) {
        return vehicleTypes[level];
    }

    public static int getSpeed(int level) {
        return speed[level];
    }
}
