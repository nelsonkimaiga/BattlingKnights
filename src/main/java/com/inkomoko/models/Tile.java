package com.inkomoko.models;

public class Tile {
    private int row;
    private int col;
    private Knight knight;
    private Item item;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.knight = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Knight getKnight() {
        return knight;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    public void removeKnight() {
        this.knight = null;
    }

    public void setItem(Item item) {
        this.item = item;
    }


}
