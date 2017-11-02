package model;

import javafx.scene.paint.Color;

/**
 * Represents an Apple that move randomly on the board.
 *
 * @author Susanna Dong, Jim Harris
 * @version 1.0
 */
public class Apple extends GameElement {

    /**
     * Constructor for the Apple.
     */
    public Apple() {
        super(0, 0, Color.RED);
    }
}
