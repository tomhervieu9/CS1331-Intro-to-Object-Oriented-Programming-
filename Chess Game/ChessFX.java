package boardview;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import gamecontrol.AIChessController;
import gamecontrol.ChessController;
import gamecontrol.GameController;
import gamecontrol.NetworkedChessController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.beans.binding.Bindings;

/**
 * Main class for the chess application
 * Sets up the top level of the GUI
 * @author Tom Hervieu
 * @version 1.0
 */
public class ChessFX extends Application {
    private GameController controller;
    private BoardView board;
    private Text stateOfGame;
    private Text sideStatus;
    private VBox rootNode;
    private Pane pane;
    private Text ipAddress;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new ChessController();
        stateOfGame = new Text(controller.getCurrentState().toString());
        sideStatus = new Text(controller.getCurrentSide().toString());
        board = new BoardView(controller, stateOfGame, sideStatus);

        pane = board.getView();

        VBox vert = new VBox();
        HBox horz1 = new HBox();
        HBox horz2 = new HBox();
        Button resetButton = new Button();

        resetButton.setText("Reset");
        resetButton.setOnAction(event -> {
                controller = new ChessController();
                board.reset(controller);
            });

        Button playCompButton = new Button();

        playCompButton.setText("Play Computer");
        playCompButton.setOnAction(event -> {
                controller = new AIChessController();
                board.reset(controller);
            });

        HBox.setMargin(resetButton, new Insets(1, 1, 1, 4));
        HBox.setMargin(playCompButton, new Insets(1, 70, 1, 4));
        HBox.setMargin(sideStatus, new Insets(1, 10, 1, 1));

        Button hostButton = new Button();

        hostButton.setText("Host");
        hostButton.setOnMouseClicked(makeHostListener());

        try {
            ipAddress = new Text(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        TextField enterIP = new TextField();
        Button join = new Button();

        join.setText("Join");
        join.disableProperty().bind(Bindings.isEmpty(enterIP.textProperty()));
        join.setOnMouseClicked(makeJoinListener(enterIP));

        horz2.getChildren().addAll(hostButton, ipAddress, enterIP, join);
        horz1.getChildren().addAll(resetButton, playCompButton, sideStatus, stateOfGame);
        vert.getChildren().addAll(pane, horz1, horz2);

        Scene scene = new Scene(vert);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }

    private EventHandler<? super MouseEvent> makeHostListener() {
        return event -> {
            board.reset(new NetworkedChessController());
        };
    }

    private EventHandler<? super MouseEvent> makeJoinListener(TextField input) {
        return event -> {
            try {
                InetAddress addr = InetAddress.getByName(input.getText());
                GameController newController
                    = new NetworkedChessController(addr);
                board.reset(newController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }


    public static void main(String[] args) {
        launch(args);
    }
}
