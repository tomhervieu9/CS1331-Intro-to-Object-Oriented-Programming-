package model;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.Group;

import snake.SnakeGame;

/**
 * Represents an item that can go on the game board.
 *
 * @author Susanna, Jim
 */
public abstract class GameElement {
    private Rectangle graphic;
    public static final int SIDE_LENGTH = SnakeGame.TILE_WIDTH;

    /**
     * Constructor for the class.
     */
    public GameElement() {
        this(0, 0, Color.GREEN);
    }

    /**
     * Constructor for the class.
     *
     * @param x the x coordinate of the element on the screen.
     * @param y the y coordinate of the element on the screen.
     * @param color the color of the element.
     */
    public GameElement(double x, double y, Color color) {
        graphic = new Rectangle(SIDE_LENGTH, SIDE_LENGTH, color);
        graphic.setX(x);
        graphic.setY(y);
    }

    /**
     * @return a double representing the x coordinate of the element.
     */
    public double getX() {
        return graphic.getX();
    }

    /**
     * @return a double representing the y coordinate of the element.
     */
    public double getY() {
        return graphic.getY();
    }

    /**
     * Sets the x coordinate of the element.
     *
     * @param x the new x coordinate of the element.
     */
    public void setX(double x) {
        graphic.setX(x);
    }

    /**
     * Sets the y coordinate of the element.
     *
     * @param y the new x coordinate of the element.
     */
    public void setY(double y) {
        graphic.setY(y);
    }

    /**
     * Adds the element to the given scene.
     *
     * @param scene the Scene to add the element to.
     */
    public void addToScene(Scene scene) {
        ((Group) scene.getRoot()).getChildren().addAll(graphic);
    }

    /**
     * Whether or not this element is colliding with another.
     *
     * @param other the other GameElement that this may be colliding with.
     * @return a boolean representing whether or not there is a collison.
     */
    public boolean isColliding(GameElement other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
