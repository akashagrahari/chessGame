package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.example.game.constants.ChessConstants.BISHOP_POSITION;
import static com.example.game.constants.ChessConstants.GRID_SIZE;

/**
 * Created by akash on 25/9/18.
 */
public class BishopPieceTest {

    private BishopPiece bishopPiece;
    private Position positionFrom;
    private Position positionTo1;
    private Position positionTo2;
    private Spot spot;
    private Position positionToIllegal;

    private Spot[][] chessGrid = new Spot[GRID_SIZE][GRID_SIZE];

    @Before
    public void setup() {
        PieceTestUtils.generateGrid(chessGrid);
        bishopPiece = Mockito.spy(new BishopPiece(PlayerColour.WHITE));
        positionFrom = new Position(2,3);
        positionTo1 = new Position(4,5);
        positionTo2 = new Position(0,1);
        spot = new Spot();
        positionToIllegal = new Position(2,5);
    }

    @Test
    public void testSetInitialPosition() {
        Mockito.doReturn(0).when((Piece) bishopPiece).getStartingRow1();
        bishopPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][BISHOP_POSITION].getPiece().getPieceType(), PieceType.BISHOP);
        Assert.assertNull(chessGrid[0][GRID_SIZE-BISHOP_POSITION-1].getPiece());
        bishopPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][BISHOP_POSITION].getPiece().getPieceType(), PieceType.BISHOP);
        Assert.assertEquals(chessGrid[0][GRID_SIZE-BISHOP_POSITION-1].getPiece().getPieceType(), PieceType.BISHOP);
    }

    @Test
    public void testIsValidMoveType() {
        Mockito.doReturn(false).when((Piece) bishopPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertTrue(bishopPiece.isValidMoveType(positionFrom, positionTo1, PlayerColour.WHITE, spot));
        Assert.assertTrue(bishopPiece.isValidMoveType(positionFrom, positionTo2, PlayerColour.WHITE, spot));
        Assert.assertFalse(bishopPiece.isValidMoveType(positionFrom, positionToIllegal, PlayerColour.WHITE, spot));
        Mockito.doReturn(true).when((Piece) bishopPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertFalse(bishopPiece.isValidMoveType(positionFrom, positionTo1, PlayerColour.WHITE, spot));
        Assert.assertFalse(bishopPiece.isValidMoveType(positionFrom, positionTo2, PlayerColour.WHITE, spot));
    }

    @Test
    public void testIsMoveThroughOtherPieces() {
        Assert.assertFalse(bishopPiece.isMoveThroughOtherPieces(positionFrom, positionTo1, chessGrid));
        chessGrid[3][4] = new Spot(new BishopPiece(PlayerColour.BLACK));
        Assert.assertTrue(bishopPiece.isMoveThroughOtherPieces(positionFrom, positionTo1, chessGrid));

    }
}
