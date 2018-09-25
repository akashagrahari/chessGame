package com.example.game.model;

/**
 * Created by akash on 21/9/18.
 */
public enum PieceType {
    KING ("Kg"),
    QUEEN ("Qn"),
    ROOK ("Rk"),
    BISHOP ("Bp"),
    KNIGHT ("Kt"),
    PAWN ("Pn");

    private final String pieceTypeValue;

    PieceType(String pieceTypeValue) {
        this.pieceTypeValue = pieceTypeValue;
    }

    public String toString() {
        return this.pieceTypeValue;
    }
}
