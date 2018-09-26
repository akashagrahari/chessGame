package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;
import static com.example.game.constants.ChessConstants.GRID_SIZE;

/**
 * Created by akash on 22/9/18.
 */
public class PawnPiece extends Piece {
    public PawnPiece(PlayerColour playerColour) {
        super(PieceType.PAWN, playerColour, true);
    }

    public void setInitialPosition(Spot[][] chessGrid) {
        int startingRow2 = getStartingRow2();
        for(int i = 0; i<GRID_SIZE; i++) {
            chessGrid[startingRow2][i] = new Spot(this);
        }
    }

    @Override
    public boolean isValidMoveType(Position positionFrom, Position positionTo, PlayerColour currentPlayerColour,
                                   Spot spotTo) {
        boolean isValidMoveType = false;
        int positionXDiff = positionTo.getX() - positionFrom.getX();
        int positionYDiff = positionTo.getY() - positionFrom.getY();
        boolean moveCapturesOwnUnit = moveCapturesOwnUnit(currentPlayerColour, spotTo);
        if(((positionXDiff>0 && currentPlayerColour.equals(PlayerColour.WHITE)) ||
                ((positionXDiff<0 && currentPlayerColour.equals(PlayerColour.BLACK)))) &&
                !moveCapturesOwnUnit) {
            int absPositionXDiff = Math.abs(positionXDiff);
            int absPositionYDiff = Math.abs(positionYDiff);
            if (absPositionXDiff == 2 && absPositionYDiff == 0 && positionFrom.getX() == getStartingRow2() &&
                    spotTo.getPiece()==null) {
                isValidMoveType = true;
            } else if (absPositionXDiff == 1 && absPositionYDiff == 0 && spotTo.getPiece()==null) {
                isValidMoveType = true;
            } else if (absPositionXDiff == 1 && absPositionYDiff == 1 && spotTo.getPiece()!=null) {
                isValidMoveType = true;
            }
            return isValidMoveType;

        }
        return false;
    }

    @Override
    public boolean isMoveThroughOtherPieces(Position positionFrom, Position positionTo, Spot[][] chessGrid) {
        int startX, endX;
        if(positionFrom.getX()<positionTo.getX()) {
            startX = positionFrom.getX();
            endX = positionTo.getX();
        } else {
            startX = positionTo.getX();
            endX = positionFrom.getX();
        }
        return  ((endX-startX) > 1 && chessGrid[startX+1][positionFrom.getY()].getPiece()!=null);
    }
}
