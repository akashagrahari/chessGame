package com.example.game;

import com.example.game.model.*;
import com.example.game.model.piece.KingPiece;
import com.example.game.model.piece.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by akash on 23/9/18.
 */
public class ChessEngine {
    private Board board;
    private HashMap<PlayerColour, Player> playerHashMap;
    private PlayerColour currentPlayerColour;
    private String inputCommandFrom;
    private String inputCommandTo;
    private HashMap<PlayerColour, Position> kingPositionsMap;
    private boolean player1CheckMate;
    private boolean player2CheckMate;

    private static BufferedReader br;

    ChessEngine(Board board, HashMap<PlayerColour, Player> playerHashMap) {
        this.board = board;
        this.playerHashMap = playerHashMap;
        this.currentPlayerColour = PlayerColour.WHITE;
        this.inputCommandFrom = "";
        this.inputCommandTo = "";
        this.player1CheckMate = false;
        this.player2CheckMate = false;
        this.kingPositionsMap = new HashMap<>();
    }

    public void initializeEngine() {
        br = new BufferedReader(new InputStreamReader(System.in));
        kingPositionsMap = board.getKingPositionsMap();
    }

    public void startGame() throws IOException {
        while (!player1CheckMate && !player2CheckMate) {
            getInputCommand();
            Position positionFrom = getPositionForInput(inputCommandFrom);
            Position positionTo = getPositionForInput(inputCommandTo);
            Spot spotFrom = board.getSpot(positionFrom);
            Spot spotTo = board.getSpot(positionTo);
            if(!isMoveForCurrentPlayer(positionFrom)) {
                System.out.println("Its the other player's turn or chosen spot has no piece");
                continue;
            }
            Piece pieceToMove = spotFrom.getPiece();
            if(pieceToMove.isValidMoveType(positionFrom, positionTo, currentPlayerColour, spotTo) &&
                    !pieceToMove.isMoveThroughOtherPieces(positionFrom, positionTo, board.getChessGrid())
            ) {
                board.movePiece(spotFrom, spotTo);
                if(pieceToMove instanceof KingPiece) {
                    board.setNewPositionOfKing(currentPlayerColour, positionTo);
                }
                System.out.println("OK");
            }
            else {
                System.out.println("Invalid Move by piece");
                continue;
            }
            if(board.isCurrentPlayerCheckingOtherPlayer(currentPlayerColour)) {
                System.out.println("Check");
            }
            currentPlayerColour = currentPlayerColour.toggle();
            printChessBoard();
        }
    }

    private Position getPositionForInput(String inputCommand) {
        int x = inputCommand.charAt(0)-'1';
        int y = inputCommand.charAt(1)-'a';
        return new Position(x,y);
    }

    private void printChessBoard() {
        for(int i=0;i<8;i++) {
            for(int j=0; j<8; j++) {
                Spot spot = board.getChessGrid()[i][j];
                if(spot.getPiece()!=null) {
                    System.out.print(spot.getPiece().getPlayerColour()+"_"+spot.getPiece().getPieceType()+" ");
                }
                else{
                    System.out.print("____ ");
                }
            }
            System.out.println();
        }
    }

    private boolean isMoveForCurrentPlayer(Position positionFrom) {
        Optional<PlayerColour> actionPlayerColour = board.getPlayerColourForSpot(board.getSpot(positionFrom));
        if(actionPlayerColour.isPresent() && actionPlayerColour.get().equals(currentPlayerColour)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void getInputCommand() throws IOException {
        System.out.println("Enter input");
        String inputCommand = br.readLine();
        String[] inputCommands = inputCommand.split(" ");
        inputCommandFrom = inputCommands[0];
        inputCommandTo = inputCommands[1];
    }
}
