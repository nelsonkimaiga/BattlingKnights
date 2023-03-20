package com.inkomoko.models;

public class Knight {
    private String name;
    private int row;
    private int col;
    private Status status;
    private Tile position;
    private Item equippedItem;

    private final int BASE_ATTACK = 1;
    private final int BASE_DEFENCE = 1;

    public Knight(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.position = new Tile(row, col);
        this.status = Status.ALIVE;
    }

    public void move(Direction direction) {
        int newRow = row + direction.getRowOffset();
        int newCol = col + direction.getColOffset();
        if (isValidMove(newRow, newCol)) {
            row = newRow;
            col = newCol;
        }
    }

    private boolean isValidMove(int newRow, int newCol) {
        return newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8;
    }

    public String toString() {
        return name + " (" + row + ", " + col + ")";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Tile getPosition() {
        return position;
    }

    public void setPosition(Tile tile) {
        this.position = tile;
    }

    public boolean isDrowned() {
        return status == Status.DROWNED;
    }

    public boolean isDead() {
        return this.status == Status.DEAD;
    }

    public void setDrowned(boolean isDrowned) {
        this.status = isDrowned ? Status.DROWNED : Status.ALIVE;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTile(Tile position) {
        this.position = position;
    }

    public Status getStatus() {
        return this.status;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setPosition(int row, int col) {
        this.position = new Tile(row, col);
    }


    public int getAttackScore() {
        if (this.status == Status.ALIVE && !this.isDrowned()) {
            int attackScore = 1; // base attack score of 1
            if (this.equippedItem != null) {
                attackScore += this.equippedItem.getAttackBonus();
            }
            attackScore += 0.5; // add surprise bonus of 0.5
            return attackScore;
        } else {
            return 0; // return 0 for DEAD or DROWNED knights
        }
    }

    public int getDefenceScore() {
        if (this.isDrowned() || this.isDead()) {
            return 0;
        }
        int defenceScore = BASE_DEFENCE;
        if (equippedItem != null) {
            defenceScore += equippedItem.getDefenceBonus();
        }
        return defenceScore;
    }

    public void dropItems(Board board) {
        if (equippedItem != null) {
            Tile currentTile = board.getTile(row, col);
            currentTile.setItem(equippedItem);
            equippedItem = null;
        }
    }


}

