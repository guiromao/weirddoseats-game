package co.weirddoeats.gfx.simplegfx;

import co.weirddoeats.attributes.BackgroundFactory;
import co.weirddoeats.attributes.InfoColor;
import co.weirddoeats.grid.Grid;
import co.weirddoeats.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameGrid implements Grid {

    public static final int PADDING = 10;
    public static final int INFO_BOARD_WIDTH = 36 * 30 + 25;
    public static final int INFO_BOARD_HEIGHT = 80;
    public static final int PIXELS = 30;

    private int cols;
    private int rows;
    private Rectangle gameBoard;
    private Rectangle infoBoard;
    private Picture background;

    public GameGrid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }

    /**
     * @see Grid#init()
     */
    @Override
    public void init() {

        gameBoard = new Rectangle(PADDING, PADDING + INFO_BOARD_HEIGHT, cols * PIXELS +25, rows * PIXELS + 20);
        gameBoard.setColor(Color.WHITE);
        gameBoard.fill();

        infoBoard = new Rectangle(PADDING, PADDING, cols * PIXELS + 25, INFO_BOARD_HEIGHT);
        infoBoard.setColor(Color.WHITE);
        infoBoard.fill();

        background = new Picture(PADDING * 19 + 8, PADDING + INFO_BOARD_HEIGHT, BackgroundFactory.PICTURES[0]);
        background.grow(188, 0);
        background.delete();

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


    public SimpleGfxGridPosition makeGridPosition(int col, int row, String pic) {
        return new SimpleGfxGridPosition(col, row, this, pic);
    }

    public Rectangle getGameBoard(){
        return gameBoard;
    }

    public Rectangle getInfoBoard(){
        return infoBoard;
    }

    public Picture getBackground(){
        return background;
    }

    public void setInfoColor(Color color){
        infoBoard.setColor(color);
    }

    public void setBackground(String picture){
        background.delete();
        background = new Picture(PADDING * 19 + 8, PADDING + INFO_BOARD_HEIGHT, picture);
        background.grow(188, 0);
        background.draw();

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

    public SimpleGfxGridPosition makeGridPosition(int col, int row, String picture, boolean b) {
        return new SimpleGfxGridPosition(col, row, this, picture, b);
    }
}
