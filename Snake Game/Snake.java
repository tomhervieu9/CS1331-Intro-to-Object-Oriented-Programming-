package model;

import javafx.geometry.Point2D;
import java.util.LinkedList;
import java.util.Iterator;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;

/**
 * Represents the player controlled snake on the game board.
 *
 * @author Susanna Dong, Jim Harris
 * @version 1.0
 */
public class Snake implements Iterable<SnakeSegment> {
    private LinkedList<SnakeSegment> segments;
    private Direction direction;

    /**
     * Constructor for the class.
     *
     * @param bounds the boundaries of the board.
     * @param scene the scene that the snake belongs in.
     */
    public Snake(Rectangle bounds, Scene scene) {
        this.segments = new LinkedList<SnakeSegment>();
        this.direction = Direction.DOWN;
        addSegment(bounds, scene);
    }

    /**
     * @return a Point2D representing the coordinates of the head.
     */
    public Point2D getHeadLocation() {
        double x = segments.getFirst().getX();
        double y = segments.getFirst().getY();
        return new Point2D(x, y);
    }

    /**
     * @return the SnakeSegment at the head of the snake.
     */
    public SnakeSegment getHead() {
        return segments.getFirst();
    }

    /**
     * Adds a segment to the end of the snake and adds it to the scene.
     *
     * @param bounds the bounds the snake lives in.
     * @param scene the scene the snake belongs in.
     */
    public void addSegment(Rectangle bounds, Scene scene) {
        if (segments.size() == 0) {
            addSegment(new SnakeSegment(bounds.getWidth() / 2,
                bounds.getHeight() / 2), scene);
        } else {
            Direction d;
            double x;
            double y;

            if (segments.size() == 1) {
                Point2D headLocation = getHeadLocation();
                d = this.direction;
                x = headLocation.getX();
                y = headLocation.getY();
            } else {
                SnakeSegment next = segments.get(segments.size() - 2);
                SnakeSegment last = segments.getLast();
                x = last.getX();
                y = last.getY();
                if (last.getX() > next.getX()) {
                    d = Direction.LEFT;
                } else if (last.getX() < next.getX()) {
                    d = Direction.RIGHT;
                } else if (last.getY() > next.getY()) {
                    d = Direction.UP;
                } else {
                    d = Direction.DOWN;
                }
            }
            if (d == Direction.DOWN) {
                y -= GameElement.SIDE_LENGTH;
            } else if (d == Direction.UP) {
                y += GameElement.SIDE_LENGTH;
            } else if (d == Direction.RIGHT) {
                x -= GameElement.SIDE_LENGTH;
            } else {
                x += GameElement.SIDE_LENGTH;
            }
            addSegment(new SnakeSegment(x, y), scene);
        }
    }

    /**
     * Adds a segment to the end of the snake.
     *
     * @param p the segment to add.
     * @param scene the scene the snake belongs in.
     */
    public void addSegment(SnakeSegment p, Scene scene) {
        segments.addLast(p);
        p.addToScene(scene);
    }

    @Override
    public Iterator<SnakeSegment> iterator() {
        return segments.iterator();
    }

    /**
     * Changes the direction of the snake.
     *
     * @param direction the new direction the snake should move in.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return an int representing the length of the snake.
     */
    public int getLength() {
        return segments.size();
    }

    /**
     * Moves the snake
     */
    public void move() {
        for (int i = segments.size() - 1; i > 0; i--) {
            segments.get(i).setX(segments.get(i - 1).getX());
            segments.get(i).setY(segments.get(i - 1).getY());
        }
        if (segments.size() > 0) {
            SnakeSegment head = segments.getFirst();
            if (direction == Direction.UP) {
                head.setY(head.getY() - GameElement.SIDE_LENGTH);
            } else if (direction == Direction.DOWN) {
                head.setY(head.getY() + GameElement.SIDE_LENGTH);
            } else if (direction == Direction.LEFT) {
                head.setX(head.getX() - GameElement.SIDE_LENGTH);
            } else {
                head.setX(head.getX() + GameElement.SIDE_LENGTH);
            }
        }
    }
}
