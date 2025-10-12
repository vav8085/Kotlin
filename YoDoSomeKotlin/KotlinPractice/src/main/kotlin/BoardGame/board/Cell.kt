package org.example.BoardGame

import BoardGame.board.Position

class Cell(position: Position) {
    var piece: Piece? = null

    val isOccupied: Boolean
        get() = piece != null
}