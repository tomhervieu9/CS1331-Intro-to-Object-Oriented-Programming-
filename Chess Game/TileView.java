package boardview;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import model.Position;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * View class for a tile on a chess board
 * A tile should be able to display a chess piece
 * as well as highlight itself during the game.
 *
 * @author Tom Hervieu
 * @version 1.0
 */
public class TileView implements Tile {
    private Position position;
    private String symbol;
    private Color color;
    private Label label;
    private Node node;
    private Rectangle emptyTile;
    private Rectangle highlightTile;
    private Rectangle chessPiece;

    /**
     * Creates a TileView with a specified position
     * @param p
     */
    public TileView(Position position) {
        this.position = position;
        emptyTile = new Rectangle(75, 75, color);
        highlightTile = new Rectangle(75, 75, Color.TRANSPARENT);
        chessPiece = new Rectangle(75, 75);
        label = new Label();
        StackPane sp = new StackPane();

        sp.getChildren().clear();
        if ((position.getRow() + position.getCol()) % 2 == 0) {
            color = Color.WHITE;
        } else {
            color = Color.GREY;
        }

        sp.getChildren().addAll(emptyTile, highlightTile, label);

        this.node = sp;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Node getRootNode() {
        return node;
    }

    @Override
    public void setSymbol(String symbol) {
        label.setText(symbol);
        double size = 55;
        label.setFont(new Font(size));
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void highlight(Color color) {
        highlightTile.setFill(color);
    }

    @Override
    public void clear() {
        highlightTile.setFill(color);
    }
}
