package com.example.game;

import com.example.game.model.Board;
import com.example.game.model.Player;
import com.example.game.model.PlayerColour;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by akash on 21/9/18.
 */
public class ChessApplication {

    private ChessEngine chessEngine;
    public static void main(String args[]) throws IOException {
        ChessApplication chessApplication = new ChessApplication();
        Board board = chessApplication.createBoard();
        HashMap<PlayerColour, Player> playerHashMap = new HashMap<>();
        playerHashMap.put(PlayerColour.WHITE, chessApplication.initializePlayer(PlayerColour.WHITE));
        playerHashMap.put(PlayerColour.BLACK, chessApplication.initializePlayer(PlayerColour.BLACK));
        chessApplication.setInitialPieces(board, playerHashMap);
        chessApplication.initializeEngine(board);

        chessApplication.startGame();
    }

    private void startGame() throws IOException {
        chessEngine.startGame();
    }

    private void initializeEngine(Board board) {
        chessEngine = new ChessEngine(board);
        chessEngine.initializeEngine();
    }

    private void setInitialPieces(Board board, HashMap<PlayerColour, Player> playerHashMap) {
        board.setInitalPieces(playerHashMap.get(PlayerColour.WHITE));
        board.setInitalPieces(playerHashMap.get(PlayerColour.BLACK));
    }

    private Player initializePlayer(PlayerColour playerColour) {
        Player player = new Player(playerColour);
        player.createPieces();
        return player;
    }

    private Board createBoard() {
        Board board = new Board();
        board.createGrid();
        return board;
    }
}
