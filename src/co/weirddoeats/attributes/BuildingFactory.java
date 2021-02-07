package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BuildingFactory {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final String [] pictures = {"building1.png", "bikeBuilding.png", "motorbikeBuilding.png", "carBuilding.png", "ferrariBuilding.png", "spaceshuttleBuilding.png"};
    private static final int [] orientations = {LEFT, RIGHT};

    public static Building generateBuilding(GameGrid grid, int level){
        int col = (int) Math.floor(Math.random() * grid.getCols());
        int row = (int) Math.floor(Math.random() * grid.getRows());
        int orientation = (col < (grid.getCols() / 2) ? orientations[0] : orientations[1]);
        //SimpleGfxGridPosition pos = grid.makeGridPosition(col, row);
        return new Building("building", (pictures[level])/* + ".png")*//*, pos*/);
    }
}