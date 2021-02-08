package co.weirddoeats.gfx.simplegfx;

import co.weirddoeats.grid.GridColor;
import co.weirddoeats.grid.GridDirection;
import co.weirddoeats.grid.position.AbstractGridPosition;
import co.weirddoeats.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private final int PIXELS = 30;

    private Picture picture;
    private Rectangle rectangle;
    private GameGrid simpleGfxGrid;


    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */

    public SimpleGfxGridPosition(GameGrid grid){
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);


    }

    public SimpleGfxGridPosition(GameGrid grid, String picture){
        super((int) (Math.random() * grid.getCols() - 1), (int) (Math.random() * grid.getRows()), grid);

        simpleGfxGrid = grid;

        if(picture.contains("building")){
            this.picture = new Picture((getCol()-3) * (PIXELS) + GameGrid.PADDING - 10, (getRow()-3) * PIXELS - 10 + GameGrid.INFO_BOARD_HEIGHT, picture);
            this.picture.grow(-103, -100);
        }
        else {
            this.picture = new Picture((getCol()-3) * (PIXELS) + GameGrid.PADDING - 3, (getRow()-3) * PIXELS - 10 + GameGrid.INFO_BOARD_HEIGHT, picture);
            this.picture.grow(-80, -90);
        }

        rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING + GameGrid.PADDING + 0, getRow() * PIXELS + GameGrid.PADDING + GameGrid.INFO_BOARD_HEIGHT, PIXELS, PIXELS);
        rectangle.setColor(Color.WHITE);

        setPos(getCol(), getRow());
        rectangle.delete();
        this.picture.delete();
    }

    /**
     * Simple graphics position constructor
     * @param col position column
     * @param row position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, GameGrid grid){
        super(col, row, grid);

        simpleGfxGrid = grid;

        //rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING, getRow() * PIXELS + GameGrid.PADDING, PIXELS, PIXELS);

        setPos(getCol(),getRow());
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        rectangle.draw();
        picture.draw();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
        rectangle.delete();
        picture.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {
        switch (direction){
            case UP:
                moveUpRect(distance);
                break;

            case DOWN:
                moveDownRect(distance);
                break;

            case RIGHT:
                moveRightRect(distance);
                break;

            case LEFT:
                moveLeftRect(distance);
                break;
        }
        System.out.println("Moved to Row: " +getRow() + ". And Col: " + getCol() + ". Width: " + picture.getWidth() + ". Height: " + picture.getHeight());
    }

    public void moveUpRect(int dist) {
        int maxRowsUp;

        if (getRow() - dist < 0) {
            maxRowsUp = getRow();
        } else {
            maxRowsUp = dist;
        }

        setPos(getCol(), (getRow() - maxRowsUp));
        rectangle.translate(0, (-PIXELS * (maxRowsUp)));
        picture.translate(0, (-PIXELS * (maxRowsUp)));
    }

    public void moveDownRect(int dist){
        int maxRowsDown;

        if(getRow() + dist >= getGrid().getRows()){
            maxRowsDown = getGrid().getRows() - 1 - getRow();
        }
        else {
            maxRowsDown = dist;
        }

        setPos(getCol(), (getRow() + maxRowsDown));
        rectangle.translate(0, (PIXELS * (maxRowsDown)));
        picture.translate(0, (PIXELS * (maxRowsDown)));

    }

    public void moveRightRect(int dist){
        int maxRowsRight;

        if(getCol() + dist >= getGrid().getCols()){
            maxRowsRight = getGrid().getCols() - 1 - getCol();
        }
        else {
            maxRowsRight = dist;
        }

        setPos((getCol() + maxRowsRight), getRow());
        rectangle.translate(PIXELS * (maxRowsRight), 0);
        picture.translate(PIXELS * (maxRowsRight), 0);
    }

    public void moveLeftRect(int dist){

        int maxRowsLeft;

        if(getCol() - dist < 0){
            maxRowsLeft = getCol();
        }
        else {
            maxRowsLeft = dist;
        }

        setPos((getCol() - maxRowsLeft), getRow());
        rectangle.translate((-PIXELS * (maxRowsLeft)), 0);
        picture.translate((-PIXELS * (maxRowsLeft)), 0);
    }

    public void setPicture(String pic){
        picture.load(pic);
    }


    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {
        //picture.setColor(SimpleGfxColorMapper.getColor(color));
        super.setColor(color);
    }

}
