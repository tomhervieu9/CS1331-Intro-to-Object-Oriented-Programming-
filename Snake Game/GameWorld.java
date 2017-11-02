package engine;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import java.util.Random;
import model.Apple;
import model.SnakeSegment;
import model.Snake;
import model.Direction;
import model.GameElement;

/**
 * Represents the game board for the game.
 *
 * @author Susanna Dong, Jim Harris
 * @version 1.0
 */
public class GameWorld {
    private Rectangle bounds;
    private Snake snake;
    private Apple apple;
    private Difficulty difficulty;
    private int score;
    private Scene scene;
    private int level;
    private Random rand;

    /**
     * Constructor for the class.
     *
     * @param bounds the background rectangle for the board.
     * @param scene the scene the board needs to be drawn to.
     */
    public GameWorld(Rectangle bounds, Scene scene) {
        this(bounds, scene, Difficulty.NORMAL);
    }

    /**
     * Constructor for the class.
     *
     * @param bounds the background rectangle for the board.
     * @param scene the scene the board needs to be drawn to.
     * @param difficulty the game difficulty.
     */
    public GameWorld(Rectangle bounds, Scene scene, Difficulty difficulty) {
        this.scene = scene;
        this.bounds = bounds;
        this.snake = new Snake(bounds, scene);
        this.difficulty = difficulty;
        this.level = 1;
        this.rand = new Random();
        apple = new Apple();
        apple.addToScene(scene);
        placeApple();
    }

    /**
     * Moves the snake and tries to make it eat an apple.
     */
    public void update() {
        moveSnake();
        eat();
    }

    /**
     * Checks if the snake is dead.
     *
     * @return a boolean representing whether or not the game is over.
     */
    public boolean isGameOver() {
        return isSnakeDead();
    }

    /**
     * Moves the snake.
     */
    private void moveSnake() {
        snake.move();
    }

    /**
     * Checks if the snake hit the boundaries or itself.
     *
     * @return a boolean representing whether or not the snake is dead.
     */
    private boolean isSnakeDead() {
        Point2D snakeHead = snake.getHeadLocation();
        if (!bounds.contains(snakeHead)) {
            return true;
        }
        SnakeSegment head = snake.getHead();
        for (SnakeSegment s : snake) {
            if (s != head && s.isColliding(head)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the apple to a random location on the board.
     *
     * The apple will snap to the game grid when it moves. It does this by
     * looking at the SIDE_LENGTH constant for GameElement (all GameElements are
     * the same size). The Apple will also not be placed on the Snake.
     */
    private void placeApple() {
        // Matrix of all tiles on the game screen. True means that the tile has
        // a snake segment over it.
        boolean[][] tiles =
            new boolean[((int) bounds.getWidth()) / GameElement.SIDE_LENGTH]
                [((int) bounds.getHeight()) / GameElement.SIDE_LENGTH];
        int numSpots = tiles.length * tiles[0].length - snake.getLength();
        for (SnakeSegment s: snake) {
            int x = ((int) s.getX()) / GameElement.SIDE_LENGTH;
            int y = ((int) s.getY()) / GameElement.SIDE_LENGTH;
            // If the snake segment is on the board, mark its tile filled.
            // Otherwise, increase the number of available spots for the apple.
            if (y < tiles.length && x < tiles[0].length && y > -1 && x > -1
                && !tiles[y][x]) {
                tiles[y][x] = true;
            } else {
                numSpots++;
            }
        }
        double[][] validSpots = new double[numSpots][2];
        int i = 0;
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (!tiles[r][c]) {
                    validSpots[i][0] = c * GameElement.SIDE_LENGTH;
                    validSpots[i][1] = r * GameElement.SIDE_LENGTH;
                    i++;
                }
            }
        }

        if (validSpots.length > 0) {
            int randomIndex = rand.nextInt(validSpots.length);
            apple.setX(validSpots[randomIndex][0]);
            apple.setY(validSpots[randomIndex][1]);
        }
    }

    /**
     * Tries to make the snake eat an apple.
     *
     * If the snake is colliding with the apple, it will grow and the apple will
     * be moved to a random spot.
     */
    private void eat() {
        if (snake.getHead().isColliding(apple)) {
            score += difficulty.getScore();
            level++;
            snake.addSegment(bounds, scene);
            placeApple();
        }
    }

    /**
     * Sets the direction of the snake.
     *
     * @param d the new direction the snake should move in.
     */
    public void setDirection(Direction d) {
        this.snake.setDirection(d);
    }

    /**
     * @return an int representing the score of the current game.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return an int representing the number of milliseconds to wait before
     * moving the snake.
     */
    public int getDelayTime() {
        int time = difficulty.getBaseTime();
        for (int i = 0; i < level / 10; i++) {
            time -= Difficulty.INCREMENT;
        }
        if (time < difficulty.getMinTime()) {
            time = difficulty.getMinTime();
        }
        return time;
    }

}
