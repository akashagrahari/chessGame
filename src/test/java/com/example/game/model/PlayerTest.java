package com.example.game.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by akash on 26/9/18.
 */
public class PlayerTest {

    @Test
    public void testCreatePieces() {
        Player player = new Player(PlayerColour.BLACK);
        player.createPieces();
        Assert.assertEquals(player.getPieces().size(), 16);
    }
}
