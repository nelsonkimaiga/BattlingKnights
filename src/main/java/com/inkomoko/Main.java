package com.inkomoko;

import com.inkomoko.models.Board;
import com.inkomoko.models.Direction;
import com.inkomoko.models.Knight;
import com.inkomoko.util.InputReader;
import org.json.JSONException;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create the knights and board
        Knight[] knights = new Knight[]{
                new Knight("R", 0, 0),
                new Knight("B", 7, 0),
                new Knight("G", 7, 7),
                new Knight("Y", 0, 7),
        };

        Board board = new Board(knights);

        // Read the input file
        List<String> movements = InputReader.readInputFile("moves.txt");


        // Move the knights based on the input movements
        for (String movement : movements) {
            String[] parts = movement.split(":");
            if (parts.length < 2) {
                // Handle the error (e.g., print an error message, throw an exception, etc.)
                System.err.println("Invalid movement format: " + movement);
                continue;
            }
            String knight = parts[0];
            int index = -1;
            switch (knight) {
                case "R":
                    index = 0;
                    break;
                case "B":
                    index = 1;
                    break;
                case "G":
                    index = 2;
                    break;
                case "Y":
                    index = 3;
                    break;
                default:
                    System.err.println("Invalid knight letter: " + knight);
                    continue;
            }

            String directionStr = parts[1];
            Direction direction = Direction.valueOf(directionStr.toUpperCase());
            board.moveKnight(index, direction);
        }

        // Print the final state of the board
        board.printState();

        // write final state to JSON file
        try {
            board.saveFinalState("final_state.json");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}