package com.example.game.model;

import com.example.game.model.piece.BishopPiece;
import com.example.game.model.piece.PawnPiece;
import com.example.game.model.piece.Piece;
import com.example.game.model.piece.RookPiece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.example.game.constants.ChessConstants.GRID_SIZE;

/**
 * Created by akash on 25/9/18.
 */
public class BoardTest {

    private Board board;
    private Player player;

    @Before
    public void setup() {
        board = new Board();
        player = new Player(PlayerColour.WHITE);
    }

    private void createGridTestMethod() {
        Spot[][] grid = board.getChessGrid();
        for(int i=0; i<GRID_SIZE; i++) {
            for(int j=0; j<GRID_SIZE; j++) {
                grid[i][j] = new Spot();
            }
        }
    }

    @Test
    public void testCreateGrid() {
        Assert.assertNull(board.getChessGrid()[0][0]);
        board.createGrid();
        Assert.assertNotNull(board.getChessGrid()[0][0]);
    }

    @Test
    public void testSetInitialPieces() {
        createGridTestMethod();
        player.getPieces().add(new RookPiece(PlayerColour.WHITE));
        board.setInitalPieces(player);
        Assert.assertEquals(board.getChessGrid()[0][0].getPiece().getPieceType(), PieceType.ROOK);
    }

    @Test
    public void testGetPlayerColourForSpot() {
        Spot spot = new Spot();
        Assert.assertEquals(board.getPlayerColourForSpot(spot), Optional.empty());
        spot = new Spot(new BishopPiece(PlayerColour.BLACK));
        Assert.assertEquals(board.getPlayerColourForSpot(spot), Optional.of(PlayerColour.BLACK));
    }

    @Test
    public void testMovePiece() {
        Piece pieceFrom = new BishopPiece(PlayerColour.WHITE);
        Piece pieceTo = new PawnPiece(PlayerColour.BLACK);
        Spot spotFrom = new Spot(pieceFrom);
        Spot spotTo  = new Spot(pieceTo);
        board.movePiece(spotFrom, spotTo);
        Assert.assertNull(spotFrom.getPiece());
        Assert.assertEquals(spotTo.getPiece(), pieceFrom);
        Assert.assertFalse(pieceTo.isAlive());
    }

    @Test
    public void testGetSpot() {
        board.getChessGrid()[0][0] = new Spot();
        Assert.assertEquals(board.getSpot(new Position(0,0)), board.getChessGrid()[0][0]);
    }

    @Test
    public void testSetNewPositionOfKing() {
        Position position = new Position(0,0);
        board.setNewPositionOfKing(PlayerColour.WHITE, position);
        Assert.assertEquals(board.getKingPositionsMap().get(PlayerColour.WHITE), position);
    }
}
