package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;

public class VehicleFactory {

    private static final String [] vehicleTypes = {"person", "bicycle", "biker", "car", "ferrari", "spaceship"};
    private static final int [] speed = {1,1,1,1,1,1};
    private static final String [] pictures = {"person_up.png", "bicycle_up.png", "biker_up.png", "car_up.png", "ferrari_up.png", "spaceship_up.png"};

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
