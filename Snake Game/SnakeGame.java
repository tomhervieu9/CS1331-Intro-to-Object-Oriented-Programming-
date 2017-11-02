package snake;

import engine.Difficulty;
import engine.GameWorld;

import java.util.Scanner;

import javafx.animation.AnimationTimer;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import model.Direction;

/**
 * The main entry point for the snake program. This class handles all graphics
 * not related to the actual game (i.e the start screen and score screen), user
 * input for each screen, etc. Also handles the updating of world on a timed
 * interval.
 *
 * @author  Susanna Dong, Jim Harris
 * @version 1.0
 */
public class SnakeGame extends Application {

    private Stage window;
    private Scene startScene;
    private Scene gameScene;
    private Scene scoreScene;
    private ToggleGroup gameMode;
    private int finalScore;
    private GameWorld world;
    private long lastUpdateTime;

    public static final int SCREEN_WIDTH = 512;
    public static final int TILE_WIDTH = 32;

    @Override
    public void start(Stage stage) {
        finalScore = 0;
        window = stage;
        gameMode = new ToggleGroup();

        setupStartScene();
        window.setScene(startScene);
        window.setResizable(false);
        window.setTitle("Snake Game");
        window.show();
    }

    /**
     * Sets startScene and adds elements to it. startScene is composed of:
     *     1) A title label
     *     2) A group of radio buttons for setting the game mode
     *     3) A button that when pressed will call setupGameScene and call play
     */
    private void setupStartScene() {
        Label title = new Label("SNAKE");
        title.setFont(new Font("Arial", 20));
        RadioButton button1 = new RadioButton("Easy");
        button1.setToggleGroup(gameMode);
        button1.setUserData(Difficulty.EASY);
        RadioButton button2 = new RadioButton("Normal");
        button2.setToggleGroup(gameMode);
        button2.setUserData(Difficulty.NORMAL);
        RadioButton button3 = new RadioButton("Hard");
        button3.setToggleGroup(gameMode);
        button3.setUserData(Difficulty.HARD);
        button2.setSelected(true);
        HBox hbox = new HBox(4);
        hbox.getChildren().addAll(button1, button2, button3);

        Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                setupGameScene();
                window.setScene(gameScene);
                play();
            }
        });
        VBox vbox = new VBox(title, hbox, startButton);
        vbox.setAlignment(Pos.TOP_CENTER);
        StackPane stackpane = new StackPane(vbox);
        startScene = new Scene(stackpane, SnakeGame.SCREEN_WIDTH,
            SnakeGame.SCREEN_WIDTH);

    }

    /**
     * Sets the gameScene and adds elements to it. gameScene is composed of:
     *     1) A Rectangle for the background
     *     2) All of the elements from world
     * world handles the addition of all the game graphics to the screen with
     * the exception of the background, which you must add to gameScene
     * manually. You will need to set world in this method as well. Also, this
     * method must add an event to gameScene such that when WASD or the arrow
     * keys are pressed the snake will change direction.
     */
    private void setupGameScene() {
        Rectangle background = new Rectangle(SCREEN_WIDTH, SCREEN_WIDTH,
            Color.BLUE);
        Group group = new Group();
        gameScene = new Scene(group, SnakeGame.SCREEN_WIDTH,
            SnakeGame.SCREEN_WIDTH);
        Rectangle r = new Rectangle(SCREEN_WIDTH, SCREEN_WIDTH, Color.BLUE);
        r.setX(0);
        r.setY(0);
        group.getChildren().add(r);
        gameScene.setOnKeyPressed(keyEvent ->
            {
                if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode()
                    == KeyCode.UP) {
                    world.setDirection(Direction.UP);
                }
                if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode()
                    == KeyCode.DOWN) {
                    world.setDirection(Direction.DOWN);
                }
                if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode()
                    == KeyCode.LEFT) {
                    world.setDirection(Direction.LEFT);
                }
                if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode()
                    == KeyCode.RIGHT) {
                    world.setDirection(Direction.RIGHT);
                }
            }
        );
        world = new GameWorld(r, gameScene,
            (Difficulty) gameMode.getSelectedToggle().getUserData());
    }

    /**
     * Sets the scoreScene and adds elements to it. scoreScene is composed of:
     *     1) A label that shows the user's score from world.
     *     2) A highscore list of the top 10 scores that is composed of:
     *         a) A ListView of Nodes for player usernames.
     *             - If the player makes it into the top 10, they need to be
     *             able to set their username, so a TextField should be at the
     *             point in the list where they belong. All other fields can
     *             just be labels for existing users.
     *         b) A ListView of Integers for player scores.
     *             - If the player makes it into the top 10, they're score
     *             should be displayed at the proper place in the list.
     *         * Existing high scores can be found in highScores.csv in the
     *         resources folder.
     *     3) A button that when pressed will write the high scores in the list
     *     to highScores.csv in the resources folder in the same format in which
     *     you originally accessed them. The button should also change the scene
     *     for window to startScene.
     */
    private void setupScoreScene() {

    }

    /**
     * Starts the game loop. Assumes that the scene for window has been set to
     * gameScene and that world has been properly reset to the starting game
     * state.
     */
    public void play() {
        AnimationTimer timey = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                if (System.currentTimeMillis()
                    - lastUpdateTime > world.getDelayTime()) {
                    world.update();
                    // DO NOT MODIFY ABOVE THIS LINE


                    // DO NOT MODIFY BELOW THIS LINE
                    lastUpdateTime = System.currentTimeMillis();
                }
            }
        };
        lastUpdateTime = System.currentTimeMillis();
        timey.start();
    }
}
