package com.example.game.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by akash on 26/9/18.
 */
public class PlayerColourTest {

    @Test
    public void testToggle() {
        PlayerColour playerColour = PlayerColour.BLACK;
        Assert.assertEquals(playerColour.toggle(), PlayerColour.WHITE);
    }
}
