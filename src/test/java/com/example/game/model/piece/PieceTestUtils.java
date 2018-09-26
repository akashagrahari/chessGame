package com.example.game.model.piece;

import com.example.game.model.Spot;

import static com.example.game.constants.ChessConstants.GRID_SIZE;

/**
 * Created by akash on 26/9/18.
 */
public class PieceTestUtils {

    public static void generateGrid(Spot[][] chessGrid) {
        for(int i=0; i<GRID_SIZE; i++) {
            for(int j=0; j<GRID_SIZE; j++) {
                chessGrid[i][j] = new Spot();
            }
        }
    }
}
