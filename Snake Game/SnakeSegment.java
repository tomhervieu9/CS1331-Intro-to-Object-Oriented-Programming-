package model;

import javafx.scene.paint.Color;

/**
 * Represents a body segment of the snake.
 */
public class SnakeSegment extends GameElement {

    /**
     * The constructor for the class.
     *
     * @param x the x coordinate of the segment.
     * @param y the y coordinate of the segment.
     */
    public SnakeSegment(double x, double y) {
        super(x, y, Color.GREEN);
    }
}
