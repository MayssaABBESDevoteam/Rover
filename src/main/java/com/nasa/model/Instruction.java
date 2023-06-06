package com.nasa.model;

public enum Instruction {
    L(-1), R(1), M(0);

    private final int index;

    Instruction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}