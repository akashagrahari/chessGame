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

/**
 * Created by akash on 26/9/18.
 */
public class PawnPieceTest {
    private PawnPiece pawnPiece;
    private Position positionFrom;
    private Position positionToFirstMove;
    private Position positionToNormal;
    private Position positionToCapture;
    private Spot spot;
    private Spot spotToCapture;
    private Position positionToIllegal;

    private Spot[][] chessGrid = new Spot[GRID_SIZE][GRID_SIZE];

    @Before
    public void setup() {
        PieceTestUtils.generateGrid(chessGrid);
        pawnPiece = Mockito.spy(new PawnPiece(PlayerColour.WHITE));
        positionFrom = new Position(1,1);
        positionToFirstMove = new Position(3,1);
        positionToNormal = new Position(2,1);
        positionToCapture = new Position(2,2);
        spot = new Spot();
        spotToCapture = new Spot(new PawnPiece(PlayerColour.BLACK));
        positionToIllegal = new Position(5,3);
        chessGrid[2][2] = spot;
    }

    @Test
    public void testSetInitialPosition() {
        Mockito.doReturn(1).when((Piece) pawnPiece).getStartingRow2();
        pawnPiece.setInitialPosition(chessGrid);
        Assert.assertEquals(chessGrid[1][0].getPiece().getPieceType(), PieceType.PAWN);
        Assert.assertEquals(chessGrid[1][3].getPiece().getPieceType(), PieceType.PAWN);
    }

    @Test
    public void testIsValidMoveType() {
        Mockito.doReturn(false).when((Piece) pawnPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertTrue(pawnPiece.isValidMoveType(positionFrom, positionToFirstMove, PlayerColour.WHITE, spot));
        Assert.assertTrue(pawnPiece.isValidMoveType(positionFrom, positionToNormal, PlayerColour.WHITE, spot));
        Mockito.doReturn(false).when((Piece) pawnPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spotToCapture);
        Assert.assertTrue(pawnPiece.isValidMoveType(positionFrom, positionToCapture, PlayerColour.WHITE, spotToCapture));
        Mockito.doReturn(true).when((Piece) pawnPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spot);
        Assert.assertFalse(pawnPiece.isValidMoveType(positionFrom, positionToFirstMove, PlayerColour.WHITE, spot));
        Assert.assertFalse(pawnPiece.isValidMoveType(positionFrom, positionToNormal, PlayerColour.WHITE, spot));
        Mockito.doReturn(true).when((Piece) pawnPiece).moveCapturesOwnUnit(PlayerColour.WHITE, spotToCapture);
        Assert.assertFalse(pawnPiece.isValidMoveType(positionFrom, positionToCapture, PlayerColour.WHITE, spotToCapture));
        Assert.assertFalse(pawnPiece.isValidMoveType(positionFrom, positionToIllegal, PlayerColour.WHITE, spot));
    }

    @Test
    public void testIsMoveThroughOtherPieces() {
        Assert.assertFalse(pawnPiece.isMoveThroughOtherPieces(positionFrom, positionToFirstMove, chessGrid));
        chessGrid[2][1] = new Spot(new PawnPiece(PlayerColour.BLACK));
        Assert.assertTrue(pawnPiece.isMoveThroughOtherPieces(positionFrom, positionToFirstMove, chessGrid));
    }
}
