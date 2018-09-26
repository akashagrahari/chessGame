package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;
import static com.example.game.constants.ChessConstants.*;

/**
 * Created by akash on 22/9/18.
 */
public class RookPiece extends Piece {
    public RookPiece(PlayerColour playerColour) {
        super(PieceType.ROOK, playerColour, true);
    }

    public void setInitialPosition(Spot[][] chessGrid) {
        int startingRow1 = getStartingRow1();
        if(chessGrid[startingRow1][ROOK_POSITION].getPiece() == null) {
            chessGrid[startingRow1][ROOK_POSITION] = new Spot(this);
        }
        else if(chessGrid[startingRow1][GRID_SIZE-ROOK_POSITION-1].getPiece() == null) {
            chessGrid[startingRow1][GRID_SIZE-ROOK_POSITION-1] = new Spot(this);
        }
    }

    @Override
    public boolean isValidMoveType(Position positionFrom, Position positionTo, PlayerColour currentPlayerColour,
                                   Spot spotTo) {
        int positionXDiff = Math.abs(positionTo.getX() - positionFrom.getX());
        int positionYDiff = Math.abs(positionTo.getY() - positionFrom.getY());
        boolean moveCapturesOwnUnit = moveCapturesOwnUnit(currentPlayerColour, spotTo);
        return (positionXDiff==0 || positionYDiff==0) && !moveCapturesOwnUnit;
    }

    @Override
    public boolean isMoveThroughOtherPieces(Position positionFrom, Position positionTo, Spot[][] chessGrid) {
        int startX, startY, endX, endY;
        if(positionFrom.getX()<positionTo.getX()) {
            startX = positionFrom.getX();
            startY = positionFrom.getY();
            endX = positionTo.getX();
            endY = positionTo.getY();
        } else {
            startX = positionTo.getX();
            startY = positionTo.getY();
            endX = positionFrom.getX();
            endY = positionFrom.getY();
        }

        if(startX==endX) {
            for(int i=startY+1; i<endY; i++) {
                if (chessGrid[startX][i].getPiece()!=null) {
                    return true;
                }
            }
        }
        else if(startY==endY) {
            for(int i=startX+1; i<endX; i++) {
                if (chessGrid[i][startY].getPiece()!=null) {
                    return true;
                }
            }
        }
        return false;
    }
}
