package co.weirddoeats.game;

import co.weirddoeats.gfx.simplegfx.GameGrid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    private final int PADDING = 10;
    private final int WIDTH = 40;
    private final int HEIGHT = 40;

    private GameGrid gameGrid;
    private KeyboardController keyboard;

    public Game(){
        gameGrid = new GameGrid(WIDTH, HEIGHT);
        keyboard = new KeyboardController();
    }

    public void start(){
        gameGrid.init();

        //MouseController mouse = new MouseController();
        //mouse.init();

        keyboard.init();
        Rectangle rect = new Rectangle(100, 100, 100, 100);
        rect.setColor(Color.WHITE);
        rect.fill();
    }

}
