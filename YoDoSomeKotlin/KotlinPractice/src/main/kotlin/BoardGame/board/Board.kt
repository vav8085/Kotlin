package org.example.BoardGame

import BoardGame.board.Position

class Board(val size: Int) {
    private val grid: Array<Array<Cell>> = Array(size) { x -> Array(size) { y -> Cell(Position(x,y)) } }

    fun getCellAt(position: Position): Cell? {
        return grid[position.x][position.y]
    }

    fun getPieceAt(position: Position): Piece? {
        return grid[position.x][position.y].piece
    }

    fun placePiece(position: Position, piece: Piece) {
        grid[position.x][position.y].piece = piece
    }
}
