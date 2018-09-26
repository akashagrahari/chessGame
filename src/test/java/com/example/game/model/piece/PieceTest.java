package com.example.game.model.piece;

import com.example.game.model.PlayerColour;
import com.example.game.model.Spot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by akash on 26/9/18.
 */
public class PieceTest {

    private Piece piece;
    private Spot sameColourSpot;
    private Spot diffColourSpot;
    private Spot blankSpot;

    @Before
    public void setup() {
        piece = new BishopPiece(PlayerColour.WHITE);
        sameColourSpot = new Spot(new BishopPiece(PlayerColour.WHITE));
        diffColourSpot = new Spot(new BishopPiece(PlayerColour.BLACK));
        blankSpot = new Spot();
    }

    @Test
    public void testMoveCapturesOwnUnit() {
        Assert.assertTrue(piece.moveCapturesOwnUnit(PlayerColour.WHITE, sameColourSpot));
        Assert.assertFalse(piece.moveCapturesOwnUnit(PlayerColour.WHITE, diffColourSpot));
        Assert.assertFalse(piece.moveCapturesOwnUnit(PlayerColour.WHITE, blankSpot));
    }

    @Test
    public void testCanAttackPosition() {

    }

    @Test
    public void testKillPiece() {
        piece.killPiece();
        Assert.assertFalse(piece.isAlive());
    }

    @Test
    public void testLivePiece() {
        piece.livePiece();
        Assert.assertTrue(piece.isAlive());
    }
}
