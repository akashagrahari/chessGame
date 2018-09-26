package com.example.game.model;

import com.example.game.model.piece.Piece;
import com.example.game.model.piece.PieceFactory;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Created by akash on 21/9/18.
 */
@Getter
public class Player {
    private PlayerColour playerColour;
    private ArrayList<Piece> pieces;

    public Player(PlayerColour playerColour) {
        this.playerColour = playerColour;
        pieces = new ArrayList<>();
    }


    public void createPieces() {
        PieceFactory pieceFactory = new PieceFactory(playerColour);
        pieces.add(pieceFactory.createPiece(PieceType.ROOK));
        pieces.add(pieceFactory.createPiece(PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(PieceType.QUEEN));
        pieces.add(pieceFactory.createPiece(PieceType.KING));
        pieces.add(pieceFactory.createPiece(PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(PieceType.ROOK));

        for (int i=0; i<8; i++) {
            pieces.add(pieceFactory.createPiece(PieceType.PAWN));
        }
    }
}
