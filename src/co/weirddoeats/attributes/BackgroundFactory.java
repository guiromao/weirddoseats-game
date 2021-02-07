package co.weirddoeats.attributes;

public class BackgroundFactory {

    private static final String [] pictures = {"skate.png", "bike.png", "motorbike.png", "car.png", "ferrari.png", "spaceshuttle.png"};

    public static Background generateBackground(int level){
        return new Background(pictures[level]);
    }
}
