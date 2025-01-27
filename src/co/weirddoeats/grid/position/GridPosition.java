package co.weirddoeats.grid.position;

import co.weirddoeats.game.Game;
import co.weirddoeats.game.GamePosition;
import co.weirddoeats.grid.GridColor;
import co.weirddoeats.grid.GridDirection;

/**
 * A position on the grid that is capable of moving around
 */
public interface GridPosition {

    /**
     * Gets the position column in the grid
     *
     * @return the position column
     */
    public int getCol();

    /**
     * Gets the position row in the grid
     *
     * @return the position row
     */
    public int getRow();

    /**
     * Updates the position column and row
     *
     * @param col the new position column
     * @param row the new position row
     */
    public void setPos(int col, int row);

    /**
     * Gets the position getColor
     *
     * @return the position getColor
     */
    public GridColor getColor();

    /**
     * Changes the getColor of this grid position
     *
     * @param color the new position getColor
     */
    public void setColor(GridColor color);

    /**
     * Displays the position in the grid
     */
    public void show();

    /**
     * Hides the position in the grid
     */
    public void hide();

    /**
     * Moves this grid position towards a specified direction
     *
     * @param direction the direction to move to
     * @param distance  the number of positions to move
     */
    public void moveInDirection(GridDirection direction, int distance);

    public void moveInDirection(GridDirection direction, int distance, Game game, boolean isGoal);

    /**
     * Tests equality with another position
     *
     * @param position the position to test against
     * @return true if positions are equal
     */
    public boolean equals(GridPosition position);

}
