This JAVA program simulates a game where four knights battle on a board of 8x8 tiles. The Main.java class creates an array of four knights and a board, reads a list of movements from a text file, and moves the knights on the board based on the input movements. After all the movements are made, the final state of the board is printed, and the state is saved to a JSON file.

The Board class represents the board and the knights on it. It has a constructor that initializes the board, placing the knights on it. It also has a moveKnight method that moves a given knight in a given direction. If the knight moves off the board, it is drowned. If the knight lands on a tile that already has a knight, a battle ensues. If the attacker wins the battle, the defender dies and drops their items. If the defender wins, the attacker dies and drops their items. If both knights are dead or drowned, nothing happens.

The board is represented as a two-dimensional array of Tile objects. Each tile can contain at most one knight and a collection of items. Each Knight object has a Tile object that represents the tile it is currently on, and a Status object that represents its current status (alive, dead, or drowned). Each Item object has a name and a score.

Language: JAVA 8.0

Build Tools: Gradle

dependencies: org.json

How to run:

Import project into an Intellij IDE,Build, the run The main class.

Included input file: moves.txt

Output file: final_state.json