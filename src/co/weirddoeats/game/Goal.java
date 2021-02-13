package co.weirddoeats.game;

import co.weirddoeats.attributes.Building;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;

public class Goal {

    private final String [] PICTURES = {"happygirl.png", "hungry.png", "businesswoman.png", "genesimmons3.png", "daftpunk.png", "astronaut.png"};

    private SimpleGfxGridPosition position;

    public Goal(Building building, GameGrid grid, int level){
        position = grid.makeGridPosition(building.getPosition().getCol() + 1, building.getPosition().getRow(), PICTURES[level], true);
    }

    public SimpleGfxGridPosition getPosition(){
        return position;
    }


}
