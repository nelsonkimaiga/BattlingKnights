package com.inkomoko.models;

public enum Direction {

    NORTH(-1, 0, "NORTH"),
    EAST(0, 1, "EAST"),
    WEST(1, 0, "WEST"),
    SOUTH(0, -1, "SOUTH");

    private final int rowOffset;
    private final int colOffset;
    private final String name;

    Direction(int rowOffset, int colOffset, String name) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }
}


