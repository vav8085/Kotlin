package BoardGame.game

import BoardGame.board.Position
import BoardGame.player.Player
import BoardGame.player.PlayerColor
import org.example.BoardGame.Board

class Game(player1Name: String, player2Name: String) {
    val board = Board(8)

    val players = listOf<Player>(Player(player1Name, PlayerColor.WHITE),
        Player(player2Name, PlayerColor.BLACK))

    private var currentPlayer: Player

    private var gameState = GameState.IN_PROGRESS

    init {
        currentPlayer = players.find { it.playerColor == PlayerColor.WHITE }!!
    }

    fun switchTurn(){
        val currentPlayer = if(currentPlayer.playerColor == PlayerColor.WHITE) players[0] else players[1]
    }

    fun makeMove(from: Position, to: Position){
        val pieceToMove = board.getPieceAt(from)
        if(pieceToMove == null){
            throw IllegalArgumentException("No piece at position $from to $to")
        }else if(pieceToMove?.color != currentPlayer.playerColor){
            throw IllegalArgumentException("Its not players turn!")
        }else if(!pieceToMove.isValidMove(board, from, to)){
            throw IllegalArgumentException("Invalid move!")
        }else{
            //move piece
            board.placePiece(to, pieceToMove)
            //remove piece from previous position
            board.getCellAt(from)?.piece = null
            switchTurn()
        }
    }
}