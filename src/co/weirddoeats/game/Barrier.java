package co.weirddoeats.game;

import co.weirddoeats.attributes.GameObject;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;

import java.util.List;

public class Barrier extends GameObject {

    private List<SimpleGfxGridPosition> positions;
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

        positions.add(grid.makeGridPosition());
        if(0 == (int) Math.floor(Math.random()* 2)){
            col = 5; row = 7;
        }else{
            col = 26; row = 10;
        }
    }

    public boolean plane(){
        if(0 == (int) Math.floor(Math.random()* 2)){
            horizontal = true;
        }else{
            horizontal = false;
        }
        return horizontal;
    }


    @Override
    public SimpleGfxGridPosition getPosition() {
        return null;
    }
}
