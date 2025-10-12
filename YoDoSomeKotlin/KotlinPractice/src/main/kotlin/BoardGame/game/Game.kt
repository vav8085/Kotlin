package BoardGame.game

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
}