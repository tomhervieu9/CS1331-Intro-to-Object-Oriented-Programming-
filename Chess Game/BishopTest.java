package model.chess;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import model.Move;
import model.Piece;
import model.Position;
import model.Side;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

import model.Piece;
import model.Position;
import model.Move;
import model.Side;
import model.Board;
import model.chess.ChessBoard;

public class BishopTest extends TestCase {
    private Piece bishop;
    private Board chessboard;

    @Rule
    public Timeout timeout = Timeout.millis(200);

    @Before
    public void setUp() {
        chessboard = new ChessBoard();
        bishop = new Bishop(Side.WHITE);

    }

    @Test
    public void testMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Set<Move> moves = bishop.generateMoves(new Position(i, j));
                assertNotNull(moves);
                validateMoves(moves);
            }
        }
    }

    @Test
    public void randomCentralPositionMovesTest() {
        // Random row and column in [3, 4]
        int randomRow = (int)(Math.random() * 2) + 3;
        int randomCol = (int)(Math.random() * 2) + 3;

        Position startPos = new Position(randomRow, randomCol);

        Set<Move> validMoves = new HashSet<>();

        if (startPos.equals(new Position(3,3))) {
            validMoves.add(new Move(startPos, new Position(0, 0)));
            validMoves.add(new Move(startPos, new Position(0, 6)));
            validMoves.add(new Move(startPos, new Position(1, 1)));
            validMoves.add(new Move(startPos, new Position(1, 5)));
            validMoves.add(new Move(startPos, new Position(2, 2)));
            validMoves.add(new Move(startPos, new Position(2, 4)));
            validMoves.add(new Move(startPos, new Position(6, 0)));
            validMoves.add(new Move(startPos, new Position(6, 6)));
            validMoves.add(new Move(startPos, new Position(5, 1)));
            validMoves.add(new Move(startPos, new Position(5, 5)));
            validMoves.add(new Move(startPos, new Position(4, 2)));
            validMoves.add(new Move(startPos, new Position(4, 4)));
            validMoves.add(new Move(startPos, new Position(7, 7)));

        } else if (startPos.equals(new Position(3, 4))) {
            validMoves.add(new Move(startPos, new Position(0, 1)));
            validMoves.add(new Move(startPos, new Position(0, 7)));
            validMoves.add(new Move(startPos, new Position(1, 2)));
            validMoves.add(new Move(startPos, new Position(1, 6)));
            validMoves.add(new Move(startPos, new Position(2, 3)));
            validMoves.add(new Move(startPos, new Position(2, 5)));
            validMoves.add(new Move(startPos, new Position(6, 1)));
            validMoves.add(new Move(startPos, new Position(6, 7)));
            validMoves.add(new Move(startPos, new Position(5, 2)));
            validMoves.add(new Move(startPos, new Position(5, 6)));
            validMoves.add(new Move(startPos, new Position(4, 3)));
            validMoves.add(new Move(startPos, new Position(4, 5)));
            validMoves.add(new Move(startPos, new Position(7, 0)));

        } else if (startPos.equals(new Position(4,3))) {
            validMoves.add(new Move(startPos, new Position(1, 0)));
            validMoves.add(new Move(startPos, new Position(1, 6)));
            validMoves.add(new Move(startPos, new Position(2, 1)));
            validMoves.add(new Move(startPos, new Position(2, 5)));
            validMoves.add(new Move(startPos, new Position(3, 2)));
            validMoves.add(new Move(startPos, new Position(3, 4)));
            validMoves.add(new Move(startPos, new Position(7, 0)));
            validMoves.add(new Move(startPos, new Position(7, 6)));
            validMoves.add(new Move(startPos, new Position(6, 1)));
            validMoves.add(new Move(startPos, new Position(6, 5)));
            validMoves.add(new Move(startPos, new Position(5, 2)));
            validMoves.add(new Move(startPos, new Position(5, 4)));
            validMoves.add(new Move(startPos, new Position(0, 7)));

        } else if (startPos.equals(new Position(4, 4))) {
            validMoves.add(new Move(startPos, new Position(1, 1)));
            validMoves.add(new Move(startPos, new Position(1, 7)));
            validMoves.add(new Move(startPos, new Position(2, 2)));
            validMoves.add(new Move(startPos, new Position(2, 6)));
            validMoves.add(new Move(startPos, new Position(3, 3)));
            validMoves.add(new Move(startPos, new Position(3, 5)));
            validMoves.add(new Move(startPos, new Position(7, 1)));
            validMoves.add(new Move(startPos, new Position(7, 7)));
            validMoves.add(new Move(startPos, new Position(6, 2)));
            validMoves.add(new Move(startPos, new Position(6, 6)));
            validMoves.add(new Move(startPos, new Position(5, 3)));
            validMoves.add(new Move(startPos, new Position(5, 5)));
            validMoves.add(new Move(startPos, new Position(0, 0)));

        }

        Set<Move> generated = bishop.generateMoves(startPos);
        assertFalse("Generated Bishop moves are fewer than actual",
            generated.size() < validMoves.size());
        assertFalse("Generated Bishop moves are greater than actual",
            generated.size() > validMoves.size());

        for (Move m: generated) {
            assertTrue(generateErrorMessage("Illegal move generated.", m),
                validMoves.contains(m));
        }

        assertEquals("Generated and Valid moves are not the same", generated, validMoves);

    }

    public void validateMoves(Set<Move> moves) {
        for (Move m : moves) {
            int startCol = m.getStart().getCol();
            int startRow = m.getStart().getRow();

            int destCol = m.getDestination().getCol();
            int destRow = m.getDestination().getRow();

            boolean boundsTest =
                destCol >= 0
                && destCol < 8
                && destRow >= 0
                && destRow < 8;

            boolean legalityTest =
                (Math.abs(destCol - startCol) == Math.abs(destRow - startRow));

            assertTrue(
                generateErrorMessage("Out of bounds move generated.", m),
                boundsTest);

            assertTrue(
                generateErrorMessage("Illegal move generated.", m),
                legalityTest);
        }
    }

    public String generateErrorMessage(String msg, Move m) {
        int startCol = m.getStart().getCol();
        int startRow = m.getStart().getRow();

        int destCol = m.getDestination().getCol();
        int destRow = m.getDestination().getRow();

        StringBuilder sb = new StringBuilder();
        sb.append("Error: ");
        sb.append(msg);
        sb.append("\n");
        sb.append(m.toString());

        return sb.toString();
    }
}
