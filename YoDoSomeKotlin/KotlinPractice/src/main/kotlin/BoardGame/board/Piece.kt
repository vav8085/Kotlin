package org.example.BoardGame

import BoardGame.board.Position
import BoardGame.player.PlayerColor

abstract class Piece(val color: PlayerColor) {
    //every piece will implement this
    abstract fun isValidMove(board: Board, from: Position, to: Position): Boolean
}
