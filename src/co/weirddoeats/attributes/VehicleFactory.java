package co.weirddoeats.attributes;

public class VehicleFactory {

    private final String [] vehicleTypes = {"skate", "bike", "motorbike", "car", "ferrari", "spaceshuttle"};
    private final int [] speed = {1,2,4,8,16,20};
    private final String [] pictures = {"skate.png", "bike.png", "motorbike.png", "car.png", "ferrari.png", "spaceshuttle.png"};

    public Vehicle generateVehicle(int level){
        return null;//new Vehicle(vehicleTypes[level], speed[level], pictures[level]);
    }
}
