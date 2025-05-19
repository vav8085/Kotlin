package com.vav.KotlinDesignPatterns.CreationalPatterns.FactoryPattern

//We will create a program to represent a chess board.
//each position on the board is identified with 3 characters notation qc5 = Queen to C5
//We will have a ChessPiece interface with file, rank and name of the piece
//Our goal is to write a method createPiece which can take a notation and return its corresponding object
//createPiece(notation: String) function is a factory method
interface ChessPiece {
    val name: String
    val file: Char
    val rank: Char
}

data class Pawn(override val file: Char, override val rank: Char, override val name: String) : ChessPiece

data class Queen(override val file: Char, override val rank: Char, override val name: String) : ChessPiece

data class Rook(override val file: Char, override val rank: Char, override val name: String) : ChessPiece

data class Bishop(override val file: Char, override val rank: Char, override val name: String) : ChessPiece

//...

fun createPiece(notation: String): ChessPiece {

    //destructuring declaration ;). I am becoming pro
    val (type, file, rank) = notation.toCharArray()

    //anyways
    return when (type) {
        'Q', 'q' -> {
            Queen(file, rank, "Queen")
        }

        'P', 'p' -> {
            Pawn(file, rank, "Pawn")
        }

        'R', 'r' -> {
            Rook(file, rank, "Rook")
        }

        'B', 'b' -> {
            Bishop(file, rank, "Bishop")
        }

        else -> throw IllegalArgumentException(
            "Are you trying to create your own piece $type?, " +
                    "why not create your own game?"
        )
    }
}

fun main() {
    println(createPiece("QB5").name)
    println(createPiece("BC4").name)
    println(createPiece("PC6").name)
}

//Queen
//Bishop
//Pawn