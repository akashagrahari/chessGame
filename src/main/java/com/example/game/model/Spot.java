package com.example.game.model;

import com.example.game.model.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by akash on 21/9/18.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Spot {
    private Piece piece;
}
