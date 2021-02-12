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
<<<<<<< HEAD
        position = grid.makeGridPosition();
        if(0 == (int) Math.floor(Math.random()* 2)){
            col = 5; row = 7;
        }else{
            col = 26; row = 10;
        }
        position.setPos(col, row);
=======
        positions.add(grid.makeGridPosition());
>>>>>>> b808ef685c552c15d953f23c6aecf18546037244
    }

    public boolean plane(){
        if(0 == (int) Math.floor(Math.random()* 2)){
            horizontal = true;
        }else{
            horizontal = false;
        }
        return horizontal;
    }

<<<<<<< HEAD

=======
    @Override
    public SimpleGfxGridPosition getPosition() {
        return null;
    }
>>>>>>> b808ef685c552c15d953f23c6aecf18546037244
}
