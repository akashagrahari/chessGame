package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.example.game.constants.ChessConstants.GRID_SIZE;
import static com.example.game.constants.ChessConstants.QUEEN_POSITION;

/**
 * Created by akash on 26/9/18.
 */
public class QueenPieceTest {
    private QueenPiece queenPiece;
    private Position positionFrom;
    private Position positionToX1;
    private Position positionToX2;
    private Position positionToY1;
    private Position positionToY2;
    private Position positionToDiagnol;
    private Spot spot;
    private Position positionToIllegal;

    private Spot[][] chessGrid = new Spot[GRID_SIZE][GRID_SIZE];

    @Before
    public void setup() {
        PieceTestUtils.generateGrid(chessGrid);
        queenPiece = Mockito.spy(new QueenPiece(PlayerColour.WHITE));
        positionFrom = new Position(2,3);
        positionToX1 = new Position(4,3);
        positionToX2 = new Position(1,3);
        positionToY1 = new Position(2,1);
        positionToY2 = new Position(2,4);
        positionToDiagnol = new Position(4,5);
        spot = new Spot();
        positionToIllegal = new Position(3,5);
    }

    @Test
    public void testSetInitialPosition() {
        Mockito.doReturn(0).when((Piece) queenPiece).getStartingRow1();
        queenPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][QUEEN_POSITION].getPiece().getPieceType(), PieceType.QUEEN);
    }

    @Test
    public void testIsValidMoveType() {
        Mockito.doReturn(false).when((Piece) queenPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertTrue(queenPiece.isValidMoveType(positionFrom, positionToX1, PlayerColour.WHITE, spot));
        Assert.assertTrue(queenPiece.isValidMoveType(positionFrom, positionToX2, PlayerColour.WHITE, spot));
        Assert.assertTrue(queenPiece.isValidMoveType(positionFrom, positionToY1, PlayerColour.WHITE, spot));
        Assert.assertTrue(queenPiece.isValidMoveType(positionFrom, positionToY2, PlayerColour.WHITE, spot));
        Assert.assertTrue(queenPiece.isValidMoveType(positionFrom, positionToDiagnol, PlayerColour.WHITE, spot));
        Assert.assertFalse(queenPiece.isValidMoveType(positionFrom, positionToIllegal, PlayerColour.WHITE, spot));
        Mockito.doReturn(true).when((Piece) queenPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertFalse(queenPiece.isValidMoveType(positionFrom, positionToX1, PlayerColour.WHITE, spot));
        Assert.assertFalse(queenPiece.isValidMoveType(positionFrom, positionToX2, PlayerColour.WHITE, spot));
        Assert.assertFalse(queenPiece.isValidMoveType(positionFrom, positionToY1, PlayerColour.WHITE, spot));
        Assert.assertFalse(queenPiece.isValidMoveType(positionFrom, positionToY2, PlayerColour.WHITE, spot));
        Assert.assertFalse(queenPiece.isValidMoveType(positionFrom, positionToDiagnol, PlayerColour.WHITE, spot));
    }

    @Test
    public void testIsMoveThroughOtherPieces() {
        Assert.assertFalse(queenPiece.isMoveThroughOtherPieces(positionFrom, positionToX1, chessGrid));
        chessGrid[3][3] = new Spot(new BishopPiece(PlayerColour.BLACK));
        Assert.assertTrue(queenPiece.isMoveThroughOtherPieces(positionFrom, positionToX1, chessGrid));

    }
}
