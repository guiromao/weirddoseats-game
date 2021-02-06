package co.weirddoeats.gfx.simplegfx;

import co.weirddoeats.grid.GridColor;
import co.weirddoeats.grid.GridDirection;
import co.weirddoeats.grid.position.AbstractGridPosition;
import co.weirddoeats.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private final int PIXELS = 20;

    private Rectangle rectangle;
    private GameGrid simpleGfxGrid;


    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(GameGrid grid){
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);

        simpleGfxGrid = grid;


        rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING, getRow() * PIXELS + GameGrid.PADDING, PIXELS, PIXELS);

        setPos(getCol(), getRow());
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

        rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING, getRow() * PIXELS + GameGrid.PADDING, PIXELS, PIXELS);

        setPos(getCol(),getRow());
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        rectangle.fill();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
        rectangle.delete();
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
    }

    public void moveUpRect(int dist) {
        int maxRowsUp;

        if (getCol() - dist < 0) {
            maxRowsUp = getRow();
        } else {
            maxRowsUp = dist;
        }

        setPos(getCol(), (getRow() - maxRowsUp));
        rectangle.translate(0, (-PIXELS * (maxRowsUp)));
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
    }

    /*public void moveUpRect(int dist){
        int maxRowsUp = dist < getRow() ? dist : getRow();

        setPos(col, (row - maxRowsUp));
        rectangle.translate(0, (-PIXELS * (maxRowsUp)));

    }

    public void moveDownRect(int dist){
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setPos(col, (row + maxRowsDown));
        rectangle.translate(0, (PIXELS * (maxRowsDown)));

    }

    public void moveRightRect(int dist){
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos((col + maxRowsRight), row);
        rectangle.translate(PIXELS * (maxRowsRight), 0);
    }

    public void moveLeftRect(int dist){

        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos((col - maxRowsLeft), row);
        rectangle.translate((-PIXELS * (maxRowsLeft)), 0);
    }*/

    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {
        rectangle.setColor(SimpleGfxColorMapper.getColor(color));
        super.setColor(color);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
