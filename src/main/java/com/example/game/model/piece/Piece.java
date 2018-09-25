package com.example.game.model.piece;

import com.example.game.model.PieceType;
import com.example.game.model.PlayerColour;
import com.example.game.model.Position;
import com.example.game.model.Spot;
import lombok.Getter;

import static com.example.game.constants.ChessConstants.GRID_SIZE;

/**
 * Created by akash on 21/9/18.
 */

@Getter
public abstract class Piece implements PieceAction {
    private PieceType pieceType;
    private PlayerColour playerColour;
    private boolean alive;
    private int startingRow1;
    private int startingRow2;

    public Piece(PieceType pieceType, PlayerColour playerColour, boolean alive) {
        this.pieceType = pieceType;
        this.playerColour = playerColour;
        this.alive = alive;
        if(playerColour.equals(PlayerColour.WHITE)) {
            startingRow1 = 0;
            startingRow2 = 1;

        }
        else if(playerColour.equals(PlayerColour.BLACK)) {
            startingRow1 = GRID_SIZE-1;
            startingRow2 = GRID_SIZE-2;
        }
    }

    public boolean moveCapturesOwnUnit(PlayerColour currentPlayerTurn, Spot spotTo) {
        if(spotTo.getPiece()!=null) {
            return spotTo.getPiece().getPlayerColour().equals(currentPlayerTurn);
        }
        else {
            return false;
        }
    }

    public boolean canAttackPosition(Position piecePosition, Position positionToAttack, PlayerColour playerColour,
                                     Spot spotToAttack, Spot[][] chessGrid) {
        return isValidMoveType(piecePosition, positionToAttack, playerColour, spotToAttack) &&
                !isMoveThroughOtherPieces(piecePosition, positionToAttack, chessGrid);

    }

    public abstract void setInitialPosition(Spot[][] chessGrid);

    public void killPiece() {
        this.alive = false;
    }

    public void livePiece() {
        this.alive = true;
    }

}
