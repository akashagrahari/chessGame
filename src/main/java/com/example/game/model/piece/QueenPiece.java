package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;

import static com.example.game.constants.ChessConstants.QUEEN_POSITION;

/**
 * Created by akash on 22/9/18.
 */
public class QueenPiece extends Piece {
    public QueenPiece(PlayerColour playerColour) {
        super(PieceType.QUEEN, playerColour, true);
    }

    public void setInitialPosition(Spot[][] chessGrid) {
        int startingRow1 = getStartingRow1();
        if(chessGrid[startingRow1][QUEEN_POSITION].getPiece() == null) {
            chessGrid[startingRow1][QUEEN_POSITION] = new Spot(this);
        }
    }

    @Override
    public boolean isValidMoveType(Position positionFrom, Position positionTo, PlayerColour currentPlayerColour,
                                   Spot spotTo) {
        int positionXDiff = Math.abs(positionTo.getX() - positionFrom.getX());
        int positionYDiff = Math.abs(positionTo.getY() - positionFrom.getY());
        boolean moveCapturesOwnUnit = moveCapturesOwnUnit(currentPlayerColour, spotTo);
        return ((positionXDiff == positionYDiff) || positionXDiff==0 || positionYDiff==0) && !moveCapturesOwnUnit;
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
        } else {
            for(int x=startX+1, y=startY+1; x<endX && y<endY; x++,y++) {
                if(chessGrid[x][y].getPiece() != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
