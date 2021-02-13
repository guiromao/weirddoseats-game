package co.weirddoeats.attributes;

public class BackgroundFactory {

    public static final String [] PICTURES = {"back1_2.png", "back2.png", "back3.png", "back4.png", "back5.png", "warp.png"};

    public static Background generateBackground(int level){
        return new Background(PICTURES[level]);
    }
}
