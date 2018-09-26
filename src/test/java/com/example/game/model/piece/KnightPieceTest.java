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
import static com.example.game.constants.ChessConstants.KNIGHT_POSITION;

/**
 * Created by akash on 26/9/18.
 */
public class KnightPieceTest {
    private KnightPiece knightPiece;
    private Position positionFrom;
    private Position positionTo;
    private Spot spot;
    private Position positionToIllegal;

    private Spot[][] chessGrid = new Spot[GRID_SIZE][GRID_SIZE];

    @Before
    public void setup() {
        PieceTestUtils.generateGrid(chessGrid);
        knightPiece = Mockito.spy(new KnightPiece(PlayerColour.WHITE));
        positionFrom = new Position(3,3);
        positionTo = new Position(5,4);
        spot = new Spot();
        positionToIllegal = new Position(5,3);
    }

    @Test
    public void testSetInitialPosition() {
        Mockito.doReturn(0).when((Piece) knightPiece).getStartingRow1();
        knightPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][KNIGHT_POSITION].getPiece().getPieceType(), PieceType.KNIGHT);
    }

    @Test
    public void testIsValidMoveType() {
        Mockito.doReturn(false).when((Piece) knightPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertTrue(knightPiece.isValidMoveType(positionFrom, positionTo, PlayerColour.WHITE, spot));
        Assert.assertFalse(knightPiece.isValidMoveType(positionFrom, positionToIllegal, PlayerColour.WHITE, spot));
        Mockito.doReturn(true).when((Piece) knightPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertFalse(knightPiece.isValidMoveType(positionFrom, positionTo, PlayerColour.WHITE, spot));
    }

    @Test
    public void testIsMoveThroughSpaces() {
        Assert.assertFalse(knightPiece.isMoveThroughOtherPieces(positionFrom, positionTo, chessGrid));
    }
}
