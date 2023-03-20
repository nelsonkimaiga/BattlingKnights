package com.inkomoko.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Board {
    private Knight[] knights;
    private char[][] state;

    private Tile[][] tiles;

    private boolean[] moved;

    Item[] items = {Item.MAGIC_STAFF, Item.HELMET, Item.DAGGER, Item.AXE};

    public static final int BOARD_SIZE = 8;

    public Board(Knight[] knights) {
        this.knights = knights;
        this.tiles = new Tile[BOARD_SIZE][BOARD_SIZE];

        moved = new boolean[knights.length];
        Arrays.fill(moved, false);
        // Initialize the state of the board
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tiles[row][col] = new Tile(row, col);
            }
        }
        // Place the knights on the board
        for (int i = 0; i < knights.length; i++) {
            int row = knights[i].getRow();
            int col = knights[i].getCol();
            tiles[row][col].setKnight(knights[i]);
        }
    }


    public void moveKnight(int index, Direction direction) {
        // Check if the knight has already moved in this round

        Knight knight = knights[index];
        int x = knight.getPosition().getRow();
        int y = knight.getPosition().getCol();
        int newX = x;
        int newY = y;
        switch (direction) {
            case NORTH:
                newY--;
                break;
            case EAST:
                newX++;
                break;
            case SOUTH:
                newY++;
                break;
            case WEST:
                newX--;
                break;
        }
        if (newX < 0 || newX >= BOARD_SIZE || newY < 0 || newY >= BOARD_SIZE) {
            // Knight moves off the board and is drowned
            knight.setStatus(Status.DROWNED);
            knight.setTile(null);
            return;
        }
        Tile newTile = tiles[newX][newY];
        if (newTile.getKnight() != null) {
            // Knight already on the tile, they will fight
            Knight defender = newTile.getKnight();
            double attackerScore = knight.getAttackScore() + 0.5;
            double defenderScore = defender.getDefenceScore();
            if (knight.isDrowned() || knight.isDead()) {
                // Attacker is drowned or dead, they cannot attack
                defender.setStatus(Status.ALIVE);
                defender.setTile(newTile);
                knight.setStatus(Status.DEAD);
                knight.setTile(newTile);
                return;
            } else if (defender.isDrowned() || defender.isDead()) {
                // Defender is drowned or dead, they cannot defend
                knight.setStatus(Status.ALIVE);
                knight.setTile(newTile);
                defender.setStatus(Status.DEAD);
                defender.setTile(newTile);
                return;
            } else if (attackerScore > defenderScore) {
                // Attacker wins
                defender.dropItems(this);
                defender.setStatus(Status.DEAD);
                defender.setTile(newTile);
                knight.setStatus(Status.ALIVE);
                knight.setTile(newTile);
                return;
            } else {
                // Defender wins
                knight.dropItems(this);
                knight.setStatus(Status.DEAD);
                knight.setTile(newTile);
                defender.setStatus(Status.ALIVE);
                defender.setTile(newTile);
                return;
            }
        } else {
            // Knight moves to the new tile
            knight.setStatus(Status.ALIVE);
            knight.setTile(newTile);
            return;
        }
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }


    public Knight getKnight(int index) {
        if (index < 0 || index >= knights.length) {
            System.out.println("Invalid knight index: " + index);
            return null;
        }
        return knights[index];
    }

    public void printState() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Tile tile = tiles[i][j];
                Knight knight = tile.getKnight();
                if (knight == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(knight.getName() + " ");
                }
            }
            System.out.println();
        }
    }

    public void saveFinalState(String fileName) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        // Add knight information
        for (Knight knight : knights) {
            JSONArray knightInfo = new JSONArray();
            Tile position = knight.getPosition();
            if (position != null) {
                knightInfo.put(position.getRow());
                knightInfo.put(position.getCol());
            } else {
                knightInfo.put((Object) null);
                knightInfo.put((Object) null);
            }
            knightInfo.put(knight.getStatus().toString());
            Item equippedItem = knight.getEquippedItem();
            if (equippedItem != null) {
                knightInfo.put(equippedItem.getName());
                knightInfo.put(equippedItem.getAttackBonus());
                knightInfo.put(equippedItem.getDefenceBonus());
            } else {
                knightInfo.put((Object) null);
                knightInfo.put((Object) null);
            }
            jsonObject.put(knight.getName().toLowerCase(), knightInfo);
        }
        // Add item information
        for (Item item : items) {
            JSONArray itemInfo = new JSONArray();
            Tile position = item.getPosition();
            if (position != null) {
                itemInfo.put(position.getRow());
                itemInfo.put(position.getCol());
            } else {
                itemInfo.put((Object) null);
                itemInfo.put((Object) null);
            }
            itemInfo.put(item.isEquipped());
            jsonObject.put(item.getName().toLowerCase(), itemInfo);
        }

        // Write JSON object to file
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonObject.toString());
            System.out.println("Successfully saved final state to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving final state to file: " + e.getMessage());
        }
    }

}
