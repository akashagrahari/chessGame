package com.example.game.model.piece;

import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;

/**
 * Created by akash on 24/9/18.
 */
public interface PieceAction {
    boolean isValidMoveType(Position positionFrom, Position positionTo, PlayerColour currentPlayerColour, Spot spotTo);

    boolean isMoveThroughOtherPieces(Position positionFrom, Position positionTo, Spot[][] chessGrid);
}
