package com.example.game.model;

/**
 * Created by akash on 22/9/18.
 */
public enum PlayerColour {
    WHITE ("W"),
    BLACK ("B");

    private final String playerColourValue;

    PlayerColour(String playerColourValue) {
        this.playerColourValue = playerColourValue;
    }

    public String toString() {
        return this.playerColourValue;
    }

    public PlayerColour toggle() {
        if (this.equals(BLACK))
            return WHITE;
        else
            return BLACK;
    }
}
