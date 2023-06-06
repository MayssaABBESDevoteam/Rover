package com.nasa.model;

public class Rover {
    Position position;
    String instruction;// possible letters are L,R,M

    public Rover(Position position, String instruction) {
        this.position = position;
        this.instruction = instruction;
    }

    public Position getPosition() {
        return position;
    }

    public String getInstruction() {
        return instruction;
    }
}
