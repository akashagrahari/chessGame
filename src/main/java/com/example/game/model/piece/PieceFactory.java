package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import lombok.AllArgsConstructor;

/**
 * Created by akash on 22/9/18.
 */
@AllArgsConstructor
public class PieceFactory {

    private PlayerColour playerColour;

    public Piece createPiece(PieceType pieceType) {
        switch (pieceType) {
            case KING:
                return new KingPiece(playerColour);
            case QUEEN:
                return new QueenPiece(playerColour);
            case ROOK:
                return new RookPiece(playerColour);
            case BISHOP:
                return new BishopPiece(playerColour);
            case KNIGHT:
                return new KnightPiece(playerColour);
            case PAWN:
                return new PawnPiece(playerColour);
            default:
                throw new UnsupportedOperationException("Piece Type not recognized");
        }
    }
}
