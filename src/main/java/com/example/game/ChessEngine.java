package com.example.game;

import com.example.game.model.*;
import com.example.game.model.piece.KingPiece;
import com.example.game.model.piece.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Created by akash on 23/9/18.
 */
public class ChessEngine {
    private Board board;
    private PlayerColour currentPlayerColour;
    private String inputCommandFrom;
    private String inputCommandTo;
    private static BufferedReader br;

    ChessEngine(Board board) {
        this.board = board;
        this.currentPlayerColour = PlayerColour.WHITE;
    }

    public void initializeEngine() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startGame() throws IOException {
        while (bothKingsAlive()) {
            if(!getValidatedInputCommand()) {
                System.out.println("Invalid");
                continue;
            }
            Position positionFrom = getPositionForInput(inputCommandFrom);
            Position positionTo = getPositionForInput(inputCommandTo);
            Spot spotFrom = board.getSpot(positionFrom);
            Spot spotTo = board.getSpot(positionTo);
            if(!isMoveForCurrentPlayer(positionFrom)) {
                System.out.println("Invalid");
                continue;
            }
            Piece pieceToMove = spotFrom.getPiece();
            if(pieceToMove.isValidMoveType(positionFrom, positionTo, currentPlayerColour, spotTo) &&
                    !pieceToMove.isMoveThroughOtherPieces(positionFrom, positionTo, board.getChessGrid())) {
                board.movePiece(spotFrom, spotTo);
                if(pieceToMove instanceof KingPiece) {
                    board.setNewPositionOfKing(currentPlayerColour, positionTo);
                }
                System.out.println("OK");
            }
            else {
                System.out.println("Invalid");
                continue;
            }
            if(board.isCurrentPlayerCheckingOtherPlayer(currentPlayerColour)) {
                System.out.println("Check");
            }
            currentPlayerColour = currentPlayerColour.toggle();
            printChessBoard();
        }
        printWinner();
    }

    private void printWinner() {
        if(board.getKingPositionsMap().get(PlayerColour.WHITE)!=null) {
            System.out.println("WHITE WINS!");
        }
        else if(board.getKingPositionsMap().get(PlayerColour.BLACK)!=null) {
            System.out.println("BLACK WINS!");
        }
    }

    private boolean bothKingsAlive() {
        return board.areBothKingsAlive();
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

    private boolean getValidatedInputCommand() throws IOException {
        System.out.println("Enter input");
        String inputCommand = br.readLine();
        boolean userInputValid = userInputValid(inputCommand);
        if(userInputValid) {
            String[] inputCommands = inputCommand.split(" ");
            inputCommandFrom = inputCommands[0];
            inputCommandTo = inputCommands[1];
        }
        return userInputValid;
    }

    private boolean userInputValid(String inputCommand) {
        boolean userInputValid = true;
        if(inputCommand.length()!=5) {
            userInputValid = false;
        }
        else if (inputCommand.charAt(0)<'1' && inputCommand.charAt(0)>'8' && inputCommand.charAt(3)<'1' &&
                inputCommand.charAt(3)>'8') {
            userInputValid = false;
        }
        else if (inputCommand.charAt(1)<'a' && inputCommand.charAt(1)>'h' && inputCommand.charAt(4)<'a' &&
                inputCommand.charAt(4)>'h') {
            userInputValid = false;
        }
        else if (inputCommand.charAt(2)!=' ') {
            userInputValid = false;
        }
        else if (inputCommand.substring(0,2).equals(inputCommand.substring(3))) {
            userInputValid = false;
        }
        return userInputValid;
    }
}
