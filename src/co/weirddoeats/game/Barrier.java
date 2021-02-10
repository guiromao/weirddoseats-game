package co.weirddoeats.game;

import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;

public class Barrier {

    private SimpleGfxGridPosition position;
    private boolean horizontal;
    private int col;
    private int row;

    public Barrier(){
        plane();
    }

    public boolean isHorizontal(){
        return horizontal;
    }

    public void generatePositions(GameGrid grid) {
        position = grid.makeGridPosition();
    }

    public boolean plane(){
        if(0 == (int) Math.floor(Math.random()* 2)){
            horizontal = true;
        }else{
            horizontal = false;
        }
        return horizontal;
    }

}
