package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by akash on 26/9/18.
 */
public class PieceFactoryTest {

    @Test
    public void testCreatePiece() {
        PieceFactory pieceFactory = new PieceFactory(PlayerColour.BLACK);
        Piece piece = pieceFactory.createPiece(PieceType.KNIGHT);
        Assert.assertEquals(piece.getPieceType(), PieceType.KNIGHT);
        Assert.assertEquals(piece.getPlayerColour(), PlayerColour.BLACK);
        Assert.assertTrue(piece.isAlive());
    }
}
