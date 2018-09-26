package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;

import static com.example.game.constants.ChessConstants.KING_POSITION;

/**
 * Created by akash on 22/9/18.
 */
public class KingPiece extends Piece {

    public KingPiece(PlayerColour playerColour) {
        super(PieceType.KING ,playerColour, true);
    }

    public void setInitialPosition(Spot[][] chessGrid) {
        int startingRow1 = getStartingRow1();
        if(chessGrid[startingRow1][KING_POSITION].getPiece() == null) {
            chessGrid[startingRow1][KING_POSITION] = new Spot(this);
        }
    }

    @Override
    public boolean isValidMoveType(Position positionFrom, Position positionTo, PlayerColour currentPlayerColour,
                                   Spot spotTo) {
        int positionXDiff = Math.abs(positionTo.getX() - positionFrom.getX());
        int positionYDiff = Math.abs(positionTo.getY() - positionFrom.getY());
        boolean moveCapturesOwnUnit = moveCapturesOwnUnit(currentPlayerColour, spotTo);
        return (positionXDiff<=1 && positionYDiff<=1) && !moveCapturesOwnUnit;
    }

    @Override
    public boolean isMoveThroughOtherPieces(Position positionFrom, Position positionTo, Spot[][] chessGrid) {
        return false;
    }
}
