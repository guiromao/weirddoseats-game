package co.weirddoeats.attributes;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum InfoColor {
    LIGHT_GRAY(Color.LIGHT_GRAY),
    GREEN(Color.GREEN),
    CYAN(Color.CYAN),
    YELLOW(Color.YELLOW),
    RED(Color.RED),
    MAGENTA(Color.MAGENTA);

    private Color color;

    InfoColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

}
