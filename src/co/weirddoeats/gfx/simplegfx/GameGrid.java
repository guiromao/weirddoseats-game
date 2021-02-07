package co.weirddoeats.gfx.simplegfx;

import co.weirddoeats.game.KeyboardController;
import co.weirddoeats.grid.Grid;
import co.weirddoeats.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GameGrid implements Grid {

    public static final int PADDING = 10;
    public static final int INFO_BOARD_WIDTH = 25 * 30;
    public static final int INFO_BOARD_HEIGHT = 80;
    public static final int PIXELS = 30;

    private int cols;
    private int rows;
    private Rectangle gameBoard;
    private Rectangle infoBoard;

    public GameGrid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }

    /**
     * @see Grid#init()
     */
    @Override
    public void init() {
        /*Rectangle rect = new Rectangle(0, 0, cols*PIXELS, rows*PIXELS+20 + INFO_BOARD_HEIGHT);
        rect.setColor(Color.BLACK);
        rect.fill();*/

        gameBoard = new Rectangle(PADDING, PADDING + INFO_BOARD_HEIGHT, cols * PIXELS + 25, rows * PIXELS + 20);
        gameBoard.setColor(Color.WHITE);
        gameBoard.fill();

        infoBoard = new Rectangle(PADDING, PADDING, INFO_BOARD_WIDTH + 25, INFO_BOARD_HEIGHT);
        infoBoard.setColor(Color.WHITE);
        infoBoard.fill();
    }

    /**
     * @see Grid#getCols()
     */
    @Override
    public int getCols() {
        return cols;
    }

    /**
     * @see Grid#getRows()
     */
    @Override
    public int getRows() {
        return rows;
    }

    /**
     * Obtains the width of the grid in pixels
     * @return the width of the grid
     */
    public int getWidth() {
        return (cols * PIXELS);
    }

    /**
     * Obtains the height of the grid in pixels
     * @return the height of the grid
     */
    public int getHeight() {
        return (rows * PIXELS);
    }

    /**
     * Obtains the grid X position in the SimpleGFX canvas
     * @return the x position of the grid
     */
    public int getX() {
        return (gameBoard.getX());
    }

    /**
     * Obtains the grid Y position in the SimpleGFX canvas
     * @return the y position of the grid
     */
    public int getY() {
        return (gameBoard.getY());
    }

    /**
     * Obtains the pixel width and height of a grid position
     * @return
     */
    public int getCellSize() {
        return PIXELS;
    }

    /**
     * @see Grid#makeGridPosition()
     */
    @Override
    public SimpleGfxGridPosition makeGridPosition() {
        return new SimpleGfxGridPosition(this);
    }

    public SimpleGfxGridPosition makeGridPosition(String picture) {
        return new SimpleGfxGridPosition(this, picture);
    }
    /**
     * @see Grid#makeGridPosition(int, int)
     */
    @Override
    public SimpleGfxGridPosition makeGridPosition(int col, int row) {
        return new SimpleGfxGridPosition(col, row, this);
    }

    public Rectangle getGameBoard(){
        return gameBoard;
    }

    public Rectangle getInfoBoard(){
        return infoBoard;
    }

    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {
        return (row * PIXELS);
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {
        return (column * PIXELS);
    }
}
