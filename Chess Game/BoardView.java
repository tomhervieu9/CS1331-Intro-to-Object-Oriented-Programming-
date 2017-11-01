package boardview;

import gamecontrol.GameController;
import gamecontrol.GameState;
import gamecontrol.NetworkedChessController;
import gamecontrol.ChessController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ButtonType;
import java.util.Set;
import model.IllegalMoveException;
import model.Move;
import model.Piece;
import model.PieceType;
import model.Position;
import model.Side;
import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A class for a view for a chess board. This class must have a reference
 * to a GameController for chess playing chess
 * @author Tom Hervieu
 * @date Nov 23, 2015
 */
public class BoardView {
    protected GameController controller;
    private GridPane gridPane;
    private Tile[][] tiles;
    private Text sideStatus;
    private Text state;
    private boolean isRotated;
    private boolean clicks = true;
    private Tile currTile;
    private boolean isPossible;
    private Move makeMove;
    private List<Position> capturedPositions;
    private String symbol;
    private Set<Move> possibleMoves;
    private final String[] piecesArray = {"Queen", "Knight", "Rook", "Bishop"};
    private List<String> dialogData = Arrays.asList(piecesArray);

    /**
     * Construct a BoardView with an instance of a GameController
     * and a couple of Text object for displaying info to the user
     * @param controller The controller for the chess game
     * @param state A Text object used to display state to the user
     * @param sideStatus A Text object used to display whose turn it is
     */
    public BoardView(GameController controller, Text state, Text sideStatus) {
        this.controller = controller;
        this.state = state;
        this.sideStatus = sideStatus;
        tiles = new Tile[8][8];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y] = new TileView(new Position(x, y));
            }
        }
        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color : goldenrod;");
        reset(controller);
    }

    /**
     * Listener for clicks on a tile
     *
     * @param tile The tile attached to this listener
     * @return The event handler for all tiles.
     */
    private EventHandler<? super MouseEvent> tileListener(Tile tile) {
        return event -> {
            if (controller instanceof NetworkedChessController
                    && controller.getCurrentSide()
                    != ((NetworkedChessController) controller).getLocalSide()) {
                //not your turn!
                return;
            }
            if (clicks) {
                firstClick(tile);
            } else {
                secondClick(tile);
            }
        };
    }

    /**
     * Perform the first click functions, like displaying
     * which are the valid moves for the piece you clicked.
     * @param tile The TileView that was clicked
     */
    private void firstClick(Tile tile) {
        currTile = tile;
        tile.highlight(Color.BLUE);
        possibleMoves = controller.getMovesForPieceAt(tile.getPosition());
        for (Move m : possibleMoves) {
            tiles[m.getDestination().getRow()]
                [m.getDestination().getCol()].highlight(Color.GREEN);
        }
        clicks = false;
    }

    /**
     * Perform the second click functions, like
     * sending moves to the controller but also
     * checking that the user clicked on a valid position.
     * If they click on the same piece they clicked on for the first click
     * then you should reset to click state back to the first click and clear
     * the highlighting effected on the board.
     *
     * @param tile the TileView at which the second click occurred
     */
    private void secondClick(Tile tile) {

        if (tile == currTile) {
            tile.clear();
            for (Move m : possibleMoves) {
                tiles[m.getDestination().getRow()]
                    [m.getDestination().getCol()].clear();
            }
            clicks = true;
        }

        isPossible = false;
        for (Move m : possibleMoves) {
            if (tile.getPosition().equals(m.getDestination())) {
                clicks = true;
                try {
                    controller.makeMove(m);
                    controller.endTurn();
                    controller.beginTurn();
                } catch (IllegalMoveException e) {
                    System.out.println(e);
                }
            }
        }
    }

    /**
     * This method should be called any time a move is made on the back end.
     * It should update the tiles' highlighting and symbols to reflect the
     * change in the board state.
     *
     * @param moveMade the move to show on the view
     * @param capturedPositions a list of positions where pieces were captured
     */

    public void updateView(Move moveMade, List<Position> capturedPositions) {
        Position start = moveMade.getStart();
        Position dest = moveMade.getDestination();

        tiles[dest.getRow()][dest.getCol()]
            .setSymbol(controller.getSymbolForPieceAt(dest));
        tiles[start.getRow()][start.getCol()].setSymbol("");
        tiles[start.getRow()][start.getCol()].clear();

        for (Move m : possibleMoves) {
            tiles[m.getDestination().getRow()]
                [m.getDestination().getCol()].clear();
        }
    }

    /**
     * Asks the user which PieceType they want to promote to
     * (suggest using Alert). Then it returns the Piecetype user selected.
     *
     * @return  the PieceType that the user wants to promote their piece to
     */
    private PieceType handlePromotion() {
        ChoiceDialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);

        dialog.setTitle("Promotion!");
        dialog.setHeaderText("Select your choice");

        Optional<String> result = dialog.showAndWait();
        String selected = "cancelled.";

        if (result.isPresent()) {
            selected = result.get();
        }

        List<PieceType> types = controller.getPromotionTypes();

        if (selected.equals("Queen")) {
            return types.get(0);
        } else if (selected.equals("Bishop")) {
            return types.get(1);
        } else if (selected.equals("Knight")) {
            return types.get(2);
        } else if (selected.equals("Rook")) {
            return types.get(3);
        } else {
            return handlePromotion();
        }
    }

    /**
     * Handles a change in the GameState (ie someone in check or stalemate).
     * If the game is over, it should open an Alert and ask to keep
     * playing or exit.
     *
     * @param s The new Game State
     */
    public void handleGameStateChange(GameState s) {
        state.setText(s.toString());

        if (s.isGameOver()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Game Over");
            alert.setHeaderText(s.toString());
            alert.setContentText("Will you continue playing or exit?");

            ButtonType nGame = new ButtonType("New Game");
            ButtonType buttonTypeCancel = new ButtonType("Exit");

            alert.getButtonTypes().setAll(nGame, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == nGame) {
                controller = new ChessController();
                reset(controller);
            } else {
                Platform.exit();
            }
        }
    }

    /**
     * Updates UI that depends upon which Side's turn it is
     *
     * @param s The new Side whose turn it currently is
     */
    public void handleSideChange(Side s) {
        sideStatus.setText(s.toString());
    }

    /**
     * Resets this BoardView with a new controller.
     * This moves the chess pieces back to their original configuration
     * and calls startGame() at the end of the method
     * @param newController The new controller for this BoardView
     */
    public void reset(GameController newController) {
        if (controller instanceof NetworkedChessController) {
            ((NetworkedChessController) controller).close();
        }
        controller = newController;
        isRotated = false;
        if (controller instanceof NetworkedChessController) {
            Side mySide
                = ((NetworkedChessController) controller).getLocalSide();
            if (mySide == Side.BLACK) {
                isRotated = true;
            }
        }
        sideStatus.setText(controller.getCurrentSide() + "'s Turn");

        // controller event handlers
        // We must force all of these to run on the UI thread
        controller.addMoveListener(
                (Move move, List<Position> capturePositions) ->
                Platform.runLater(
                    () -> updateView(move, capturePositions)));

        controller.addCurrentSideListener(
                (Side side) -> Platform.runLater(
                    () -> handleSideChange(side)));

        controller.addGameStateChangeListener(
                (GameState state) -> Platform.runLater(
                    () -> handleGameStateChange(state)));

        controller.setPromotionListener(() -> handlePromotion());


        addPieces();
        controller.startGame();
        if (isRotated) {
            setBoardRotation(180);
        } else {
            setBoardRotation(0);
        }
    }

    /**
     * Initializes the gridPane object with the pieces from the GameController.
     * This method should only be called once before starting the game.
     */
    private void addPieces() {
        gridPane.getChildren().clear();
        Map<Piece, Position> pieces = controller.getAllActivePiecesPositions();
        /* Add the tiles */
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile tile = new TileView(new Position(row, col));
                gridPane.add(tile.getRootNode(),
                        1 + tile.getPosition().getCol(),
                        1 + tile.getPosition().getRow());
                GridPane.setHgrow(tile.getRootNode(), Priority.ALWAYS);
                GridPane.setVgrow(tile.getRootNode(), Priority.ALWAYS);
                getTiles()[row][col] = tile;
                tile.getRootNode().setOnMouseClicked(
                        tileListener(tile));
                tile.clear();
                tile.setSymbol("");
            }
        }
        /* Add the pieces */
        for (Piece p : pieces.keySet()) {
            Position placeAt = pieces.get(p);
            getTileAt(placeAt).setSymbol(p.getType().getSymbol(p.getSide()));
        }
        /* Add the coordinates around the perimeter */
        for (int i = 1; i <= 8; i++) {
            Text coord1 = new Text((char) (64 + i) + "");
            GridPane.setHalignment(coord1, HPos.CENTER);
            gridPane.add(coord1, i, 0);

            Text coord2 = new Text((char) (64 + i) + "");
            GridPane.setHalignment(coord2, HPos.CENTER);
            gridPane.add(coord2, i, 9);

            Text coord3 = new Text(9 - i + "");
            GridPane.setHalignment(coord3, HPos.CENTER);
            gridPane.add(coord3, 0, i);

            Text coord4 = new Text(9 - i + "");
            GridPane.setHalignment(coord4, HPos.CENTER);
            gridPane.add(coord4, 9, i);
        }
    }

    private void setBoardRotation(int degrees) {
        gridPane.setRotate(degrees);
        for (Node n : gridPane.getChildren()) {
            n.setRotate(degrees);
        }
    }

    /**
     * Gets the view to add to the scene graph
     * @return A pane that is the node for the chess board
     */
    public Pane getView() {
        return gridPane;
    }

    /**
     * Gets the tiles that belong to this board view
     * @return A 2d array of TileView objects
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    private Tile getTileAt(int row, int col) {
        return getTiles()[row][col];
    }

    private Tile getTileAt(Position p) {
        return getTileAt(p.getRow(), p.getCol());
    }

}
