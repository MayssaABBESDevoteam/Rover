package com.nasa.model;

import java.util.Arrays;

public enum Orientation {
    N(0), E(1), S(2), W(3);

    private final int index;

    Orientation(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static Orientation nextOrientation(Orientation orientation, Instruction instruction) {
        int index = (orientation.getIndex() + instruction.getIndex()) % 4;
        int nextIndex = index < 0 ? 4 + index : index;
        return Arrays.stream(values()).filter(o -> o.getIndex() == nextIndex).findFirst()
                .orElseThrow(() -> new IllegalStateException("wrong orientation index"));
    }
}
