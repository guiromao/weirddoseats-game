package co.weirddoeats.gfx.simplegfx;

import co.weirddoeats.game.Game;
import co.weirddoeats.game.GamePosition;
import co.weirddoeats.grid.GridColor;
import co.weirddoeats.grid.GridDirection;
import co.weirddoeats.grid.position.AbstractGridPosition;
import co.weirddoeats.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private final int PIXELS = 30;

    private Picture picture;
    private Rectangle rectangle;
    private GameGrid simpleGfxGrid;

    public SimpleGfxGridPosition(GameGrid grid) {
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);

        simpleGfxGrid = grid;

        rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING + GameGrid.PADDING + 0, getRow() * PIXELS + GameGrid.PADDING + GameGrid.INFO_BOARD_HEIGHT, PIXELS, PIXELS);
        rectangle.setColor(Color.RED);
    }

    public SimpleGfxGridPosition(GameGrid grid, String picture) {
        super((int) (Math.random() * grid.getCols() - 2), (int) (Math.random() * grid.getRows()), grid);

        simpleGfxGrid = grid;

        /*if (picture.contains("building")) {
            this.picture = new Picture((getCol() - 3) * (PIXELS) + GameGrid.PADDING + 8, (getRow() - 3) * PIXELS - 10 + GameGrid.INFO_BOARD_HEIGHT, picture);
            this.picture.grow(-103, -100);
        } else {*/
            this.picture = new Picture((getCol() - 4) * (PIXELS) + GameGrid.PADDING - 5, (getRow() - 4) * PIXELS - 10 + GameGrid.INFO_BOARD_HEIGHT, picture);
            this.picture.grow(-120, -120);
        //}

        rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING + GameGrid.PADDING + 0, getRow() * PIXELS + GameGrid.PADDING + GameGrid.INFO_BOARD_HEIGHT, PIXELS, PIXELS);
        rectangle.setColor(Color.WHITE);

        setPos(getCol(), getRow());
        rectangle.delete();
        this.picture.delete();
    }

    public SimpleGfxGridPosition(int col, int row, GameGrid grid) {
        super(col, row, grid);

    }

    /**
     * Simple graphics position constructor
     *
     * @param col  position column
     * @param row  position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, GameGrid grid, String picture) {
        super(col, row, grid);

        simpleGfxGrid = grid;

        rectangle = new Rectangle(col * PIXELS + GameGrid.PADDING + GameGrid.PADDING + 0, row * PIXELS + GameGrid.PADDING + GameGrid.INFO_BOARD_HEIGHT, PIXELS, PIXELS);
        rectangle.setColor(Color.WHITE);

        //rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING, getRow() * PIXELS + GameGrid.PADDING, PIXELS, PIXELS);

        if (picture.contains("building")) {
            this.picture = new Picture((getCol() - 3) * (PIXELS) + GameGrid.PADDING + 8, (getRow() - 3) * PIXELS - 10 + GameGrid.INFO_BOARD_HEIGHT, picture);
            this.picture.grow(-103, -100);
        }

        else {
            this.picture = new Picture((col - 4) * (PIXELS) + GameGrid.PADDING + 5, (row - 3) * PIXELS - 2 + GameGrid.INFO_BOARD_HEIGHT, picture);
            this.picture.grow(-95, -95);
        }

        setPos(col, row);
        hide();
    }

    public SimpleGfxGridPosition(int col, int row, GameGrid grid, String picture, boolean b) {
        super(col, row, grid);

        simpleGfxGrid = grid;

        rectangle = new Rectangle(col * PIXELS + GameGrid.PADDING + GameGrid.PADDING + 0, row * PIXELS + GameGrid.PADDING + GameGrid.INFO_BOARD_HEIGHT, PIXELS, PIXELS);
        rectangle.setColor(Color.WHITE);

        //rectangle = new Rectangle(getCol() * PIXELS + GameGrid.PADDING, getRow() * PIXELS + GameGrid.PADDING, PIXELS, PIXELS);
        this.picture = new Picture((col - 6) * (PIXELS) , (row - 8) * PIXELS + 5 + GameGrid.INFO_BOARD_HEIGHT , picture);
        this.picture.grow(-195, -195);

        setPos(col + 1, row);
        hide();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        //rectangle.draw();
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
    public void moveInDirection(GridDirection direction, int distance, Game game, boolean isGoal) {
        switch (direction) {
            case UP:
                moveUpRect(distance, game, isGoal);
                break;

            case DOWN:
                moveDownRect(distance, game, isGoal);
                break;

            case RIGHT:
                moveRightRect(distance, game, isGoal);
                break;

            case LEFT:
                moveLeftRect(distance, game, isGoal);
                break;
        }
        System.out.println("Moved to Row: " + getRow() + ". And Col: " + getCol() + ". Width: " + picture.getWidth() + ". Height: " + picture.getHeight());
    }

    public boolean isAround(int col, int row, SimpleGfxGridPosition pos){
        return ((col == pos.getCol() && row == pos.getRow()) || (col - 1 == pos.getCol() && row == pos.getRow()) ||
                (col+1 == pos.getCol() && row == pos.getRow()) || (col == pos.getCol() && row + 1 == pos.getRow()) ||
                (col == pos.getCol() && row-1 == pos.getRow()) || (col -1 == pos.getCol() && row-1 == pos.getRow()) ||
                (col + 1 == pos.getCol() && row + 1 == pos.getRow()) ||
                (col - 2 == pos.getCol() && row == pos.getRow()) ||
                (col + 2 == pos.getCol() && row == pos.getRow()) ||
                (col == pos.getCol() && row - 2 == pos.getRow()) ||
                (col == pos.getCol() && row + 2 == pos.getRow()));
    }

    public void checkHorizontally(int moveCols, Game game, boolean isGoal) {

        GamePosition[] positions = game.getGamePositions();

        for (int i = 1; i <= Math.abs(moveCols); i++) {
            System.out.println("Passing on " + (getCol() + 1) + ", " + (getRow()) + " and Food is in " + positions[2].getPosition().getCol() + ", " +
                    positions[2].getPosition().getRow());

            if (!isGoal) {

                /*
                if ((getCol() + i) == positions[2].getPosition().getCol()
                        && (getRow()) == positions[2].getPosition().getRow())*/
                if(isAround(getCol() + i, getRow(), positions[2].getPosition())){
                    System.out.println("Passed by FOOD!");
                    game.pickFood();
                }
            } else /*if ((getCol() + i) == positions[3].getPosition().getCol()
                    && (getRow()) == positions[3].getPosition().getRow()) */
                if(isAround(getCol() + i, getRow(), positions[3].getPosition())){
                System.out.println("Passed by GOAL!");
                game.arrivedToGoal();
            }
            setPos(getCol() + moveCols, getRow());
            rectangle.translate((PIXELS * moveCols), 0);
            picture.translate((PIXELS * moveCols), 0);
        }

        /*
        setPos(getCol() + moveCols, getRow());
        rectangle.translate((PIXELS * moveCols), 0);
        picture.translate((PIXELS * moveCols), 0);*/
    }

    public void checkVertically(int moveRows, Game game, boolean isGoal) {

        GamePosition[] positions = game.getGamePositions();

        for (int i = 1; i <= Math.abs(moveRows); i++) {
            System.out.println("Passing on " + getCol() + ", " + (getRow() + i) + " and Food is in " + positions[2].getPosition().getCol() + ", " +
                    positions[2].getPosition().getRow());
            if (!isGoal) {

                if (isAround(getCol(), getRow() + i, positions[2].getPosition())) {
                    System.out.println("Passed by FOOD!");
                    game.pickFood();
                }
            } else if (isAround(getCol(), getRow() + i, positions[3].getPosition())) {
                System.out.println("Passed by GOAL!");
                game.arrivedToGoal();
            }
        }

        setPos(getCol(), getRow() + moveRows);
        rectangle.translate(0, (PIXELS * moveRows));
        picture.translate(0, (PIXELS * moveRows));
    }

    public void moveUpRect(int dist, Game game, boolean isGoal) {
        int maxRowsUp;

        if (getRow() - dist < 0) {
            maxRowsUp = getRow();
        } else {
            maxRowsUp = dist;
        }

        checkVertically((-maxRowsUp), game, isGoal);

    }

    public void moveDownRect(int dist, Game game, boolean isGoal) {
        int maxRowsDown;

        if (getRow() + dist >= getGrid().getRows()) {
            maxRowsDown = getGrid().getRows() - 1 - getRow();
        } else {
            maxRowsDown = dist;
        }

        checkVertically(maxRowsDown, game, isGoal);
        /*setPos(getCol(), (getRow() + maxRowsDown));
        rectangle.translate(0, (PIXELS * (maxRowsDown)));
        picture.translate(0, (PIXELS * (maxRowsDown)));*/

    }

    public void moveRightRect(int dist, Game game, boolean isGoal) {
        int maxRowsRight;

        if (getCol() + dist >= getGrid().getCols()) {
            maxRowsRight = getGrid().getCols() - 1 - getCol();
        } else {
            maxRowsRight = dist;
        }

        checkHorizontally(maxRowsRight, game, isGoal);
        /*
        setPos((getCol() + maxRowsRight), getRow());
        rectangle.translate(PIXELS * (maxRowsRight), 0);
        picture.translate(PIXELS * (maxRowsRight), 0);*/
    }

    public void moveLeftRect(int dist, Game game, boolean isGoal) {

        int maxRowsLeft;

        if (getCol() - dist < 0) {
            maxRowsLeft = getCol();
        } else {
            maxRowsLeft = dist;
        }

        checkHorizontally((-maxRowsLeft), game, isGoal);
        /*
        setPos((getCol() - maxRowsLeft), getRow());
        rectangle.translate((-PIXELS * (maxRowsLeft)), 0);
        picture.translate((-PIXELS * (maxRowsLeft)), 0);*/
    }

    public void setPicture(String pic) {
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
