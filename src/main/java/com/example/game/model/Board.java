package com.example.game.model;

import com.example.game.model.piece.Piece;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static com.example.game.constants.ChessConstants.*;

/**
 * Created by akash on 21/9/18.
 */
@Getter
public class Board {
    private Spot chessGrid[][];
    private HashMap<PlayerColour, Position> kingPositionsMap;

    public Board() {
        chessGrid = new Spot[GRID_SIZE][GRID_SIZE];
        kingPositionsMap = new HashMap<>();
    }


    public void setInitalPieces(Player player) {
        ArrayList<Piece> pieces =  player.getPieces();
        for(int i=0; i<pieces.size(); i++) {
            Piece piece = pieces.get(i);
            piece.setInitialPosition(chessGrid);
            if(piece.getPieceType().equals(PieceType.KING)) {
                kingPositionsMap.put(piece.getPlayerColour(), new Position(piece.getStartingRow1(), KING_POSITION));
            }
        }
    }

    public void createGrid() {
        for(int i=0; i<chessGrid.length; i++) {
            for(int j=0; j<chessGrid[0].length; j++) {
                chessGrid[i][j] = new Spot();
            }
        }
    }

    public Optional<PlayerColour> getPlayerColourForSpot(Spot spotFrom) {
        if (spotFrom.getPiece() == null) {
            return Optional.empty();
        }
        else {
            return Optional.of(spotFrom.getPiece().getPlayerColour());
        }
    }

    public void movePiece(Spot spotFrom, Spot spotTo) {
        if (spotTo.getPiece()!=null) {
            spotTo.getPiece().killPiece();
            if(spotTo.getPiece().getPieceType().equals(PieceType.KING)) {
                kingPositionsMap.put(spotTo.getPiece().getPlayerColour(), null);
            }
        }
        spotTo.setPiece(spotFrom.getPiece());
        spotFrom.setPiece(null);
    }

    private boolean player1CheckPlayer2(PlayerColour player1Colour, Position player2KingPosition) {
        Spot player2KingSpot = chessGrid[player2KingPosition.getX()][player2KingPosition.getY()];
        for(int i=0; i<chessGrid.length; i++) {
            for(int j=0; j<chessGrid[0].length; j++) {
                Spot spot = chessGrid[i][j];
                Position piecePosition = new Position(i,j);
                if(spot.getPiece()!=null && spot.getPiece().isAlive() &&
                        spot.getPiece().getPlayerColour().equals(player1Colour) &&
                spot.getPiece().canAttackPosition(piecePosition, player2KingPosition, player1Colour,
                        player2KingSpot, chessGrid)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Spot getSpot(Position position) {
        return chessGrid[position.getX()][position.getY()];
    }

    public boolean isCurrentPlayerCheckingOtherPlayer(PlayerColour currentPlayerColour) {
        Position otherPlayerKingPosition = kingPositionsMap.get(currentPlayerColour.toggle());
        boolean currentPlayerCheckingOtherPlayer = false;
        if(otherPlayerKingPosition!=null) {
            currentPlayerCheckingOtherPlayer = player1CheckPlayer2(currentPlayerColour, otherPlayerKingPosition);
        }
        return currentPlayerCheckingOtherPlayer;
    }

    public void setNewPositionOfKing(PlayerColour currentPlayerColour, Position positionTo) {
        kingPositionsMap.put(currentPlayerColour, positionTo);
    }

    public boolean areBothKingsAlive() {
        Position whiteKingPosition = kingPositionsMap.get(PlayerColour.WHITE);
        Position blackKingPositon = kingPositionsMap.get(PlayerColour.BLACK);
        return (whiteKingPosition!=null && blackKingPositon!=null);
    }
}
