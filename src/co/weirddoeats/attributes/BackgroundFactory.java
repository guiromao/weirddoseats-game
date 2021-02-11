package co.weirddoeats.attributes;

public class BackgroundFactory {

    public static final String [] PICTURES = {"bg4.png", "bg5.png", "bg6.png", "bg7.png", "bg8.png", "bg9.png"};

    public static Background generateBackground(int level){
        return new Background(PICTURES[level]);
    }
}
