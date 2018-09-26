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
import static com.example.game.constants.ChessConstants.KING_POSITION;

/**
 * Created by akash on 26/9/18.
 */
public class KingPieceTest {
    private KingPiece kingPiece;
    private Position positionFrom;
    private Position positionTo;
    private Spot spot;
    private Position positionToIllegal;

    private Spot[][] chessGrid = new Spot[GRID_SIZE][GRID_SIZE];

    @Before
    public void setup() {
        PieceTestUtils.generateGrid(chessGrid);
        kingPiece = Mockito.spy(new KingPiece(PlayerColour.WHITE));
        positionFrom = new Position(2,3);
        positionTo = new Position(3,3);
        spot = new Spot();
        positionToIllegal = new Position(2,5);
    }

    @Test
    public void testSetInitialPosition() {
        Mockito.doReturn(0).when((Piece) kingPiece).getStartingRow1();
        kingPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[0][KING_POSITION].getPiece().getPieceType(), PieceType.KING);
    }

    @Test
    public void testIsValidMoveType() {
        Mockito.doReturn(false).when((Piece) kingPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertTrue(kingPiece.isValidMoveType(positionFrom, positionTo, PlayerColour.WHITE, spot));
        Assert.assertFalse(kingPiece.isValidMoveType(positionFrom, positionToIllegal, PlayerColour.WHITE, spot));
        Mockito.doReturn(true).when((Piece) kingPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertFalse(kingPiece.isValidMoveType(positionFrom, positionTo, PlayerColour.WHITE, spot));
    }

    @Test
    public void testIsMoveThroughSpaces() {
        Assert.assertFalse(kingPiece.isMoveThroughOtherPieces(positionFrom, positionTo, chessGrid));
    }

}
