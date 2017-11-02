package model;

/**
 * Represents a direction in which the snake can travel.
 *
 * Can have one of four values; LEFT, RIGHT, UP, or DOWN. deltaX and deltaY form
 * a unit vector in the direction of the movement of the Snake.
 *
 * @author Susanna Dong, Jim Harris
 * @version 1.0
 */
public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

    private final int deltaX;
    private final int deltaY;

    /**
     * Constructor for the enum.
     * @param deltaX change in x corresponding to the direction.
     * @param deltaY change in y corresponding to the direction.
     */
    private Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * @return an int with the value of deltaX
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * @return an int with the value of deltaY
     */
    public int getDeltaY() {
        return deltaY;
    }
}
