package co.weirddoeats.game;

import co.weirddoeats.attributes.Building;
import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;

public class Goal {

    private final String PICTURE = "hungry.png";

    private SimpleGfxGridPosition position;

    public Goal(Building building, GameGrid grid){
        position = grid.makeGridPosition(building.getPosition().getCol() + 1, building.getPosition().getRow(), PICTURE, true);
    }

    public SimpleGfxGridPosition getPosition(){
        return position;
    }


}
