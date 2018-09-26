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
import static com.example.game.constants.ChessConstants.ROOK_POSITION;

/**
 * Created by akash on 26/9/18.
 */
public class RookPieceTest {
    private RookPiece rookPiece;
    private Position positionFrom;
    private Position positionToX1;
    private Position positionToX2;
    private Position positionToY1;
    private Position positionToY2;
    private Spot spot;
    private Position positionToIllegal;

    private Spot[][] chessGrid = new Spot[GRID_SIZE][GRID_SIZE];

    @Before
    public void setup() {
        PieceTestUtils.generateGrid(chessGrid);
        rookPiece = Mockito.spy(new RookPiece(PlayerColour.WHITE));
        positionFrom = new Position(2,3);
        positionToX1 = new Position(4,3);
        positionToX2 = new Position(1,3);
        positionToY1 = new Position(2,1);
        positionToY2 = new Position(2,4);
        spot = new Spot();
        positionToIllegal = new Position(3,5);
    }

    @Test
    public void testSetInitialPosition() {
        Mockito.doReturn(0).when((Piece) rookPiece).getStartingRow1();
        rookPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][ROOK_POSITION].getPiece().getPieceType(), PieceType.ROOK);
        Assert.assertNull(chessGrid[0][GRID_SIZE-ROOK_POSITION-1].getPiece());
        rookPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][ROOK_POSITION].getPiece().getPieceType(), PieceType.ROOK);
        Assert.assertEquals(chessGrid[0][GRID_SIZE-ROOK_POSITION-1].getPiece().getPieceType(), PieceType.ROOK);
    }

    @Test
    public void testIsValidMoveType() {
        Mockito.doReturn(false).when((Piece) rookPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertTrue(rookPiece.isValidMoveType(positionFrom, positionToX1, PlayerColour.WHITE, spot));
        Assert.assertTrue(rookPiece.isValidMoveType(positionFrom, positionToX2, PlayerColour.WHITE, spot));
        Assert.assertTrue(rookPiece.isValidMoveType(positionFrom, positionToY1, PlayerColour.WHITE, spot));
        Assert.assertTrue(rookPiece.isValidMoveType(positionFrom, positionToY2, PlayerColour.WHITE, spot));
        Assert.assertFalse(rookPiece.isValidMoveType(positionFrom, positionToIllegal, PlayerColour.WHITE, spot));
        Mockito.doReturn(true).when((Piece) rookPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertFalse(rookPiece.isValidMoveType(positionFrom, positionToX1, PlayerColour.WHITE, spot));
        Assert.assertFalse(rookPiece.isValidMoveType(positionFrom, positionToX2, PlayerColour.WHITE, spot));
        Assert.assertFalse(rookPiece.isValidMoveType(positionFrom, positionToY1, PlayerColour.WHITE, spot));
        Assert.assertFalse(rookPiece.isValidMoveType(positionFrom, positionToY2, PlayerColour.WHITE, spot));
    }

    @Test
    public void testIsMoveThroughOtherPieces() {
        Assert.assertFalse(rookPiece.isMoveThroughOtherPieces(positionFrom, positionToX1, chessGrid));
        chessGrid[3][3] = new Spot(new BishopPiece(PlayerColour.BLACK));
        Assert.assertTrue(rookPiece.isMoveThroughOtherPieces(positionFrom, positionToX1, chessGrid));

    }
}
