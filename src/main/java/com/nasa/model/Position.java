package com.nasa.model;

public class Position {
    private int x;// min 0 , max = 5
    private int y; // min 0 , max 5
    private Orientation orientation;// possible letters are N, S,E,W

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String toString() {
        char space = ' ';
        return String.valueOf(x) + space + y + space + orientation;
    }
}
