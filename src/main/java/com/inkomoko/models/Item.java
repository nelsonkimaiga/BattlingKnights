package com.inkomoko.models;

public enum Item {
    NONE("None",  0, 0),
    AXE("Axe", 2, 0),
    DAGGER("Dagger", 1, 0),
    HELMET("Helmet", 0, 1),
    MAGIC_STAFF("Magic Staff", 1, 1);

    private final String name;
    private final int attackBonus;
    private final int defenceBonus;
    private boolean equipped;
    private Tile position;
    private Item(String name, int attackBonus, int defenceBonus) {
        this.name = name;
        this.attackBonus = attackBonus;
        this.defenceBonus = defenceBonus;
        this.equipped = false;
    }

    public String getName() {
        return name;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenceBonus() {
        return defenceBonus;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public Tile getPosition() {
        return position;
    }
}
