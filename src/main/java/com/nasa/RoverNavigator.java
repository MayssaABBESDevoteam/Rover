package com.nasa;

import com.nasa.exception.RoverNavigatorException;
import com.nasa.model.Instruction;
import com.nasa.model.Orientation;
import com.nasa.model.Position;
import com.nasa.model.Rover;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.nasa.Validator.*;

public class RoverNavigator {
    private List<String> inputs = new ArrayList<>();
    private List<Rover> rovers = new ArrayList<>();
    private int maxX;
    private int maxY;

    public void execute(String input) {
        extractInput(input);
        if (!inputs.isEmpty()) {
            extractMaxCoordinate(inputs);
            initRovers();
            navigate();
        } else {
            throw new RoverNavigatorException("No input data was provided");
        }
        printOutput();
    }

    private void extractInput(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             Scanner sc = new Scanner(fileInputStream)) {
            while (sc.hasNextLine()) {
                inputs.add(sc.nextLine());
            }
        } catch (IOException e) {
            throw new RoverNavigatorException(
                    String.format("Problem occurred during input file parse : %s", e.getMessage()));
        }
    }

    private void extractMaxCoordinate(List<String> inputs) {
        String maxCoordinateLine = inputs.get(0);
        validateMaxCoordinate(maxCoordinateLine);
        String[] inputSplit = maxCoordinateLine.split("\\s+");
        maxX = Integer.parseInt(inputSplit[0]);
        maxY = Integer.parseInt(inputSplit[1]);
    }

    private void initRovers() {
        validateInputLinesNumber(inputs);
        for (int i = 1; i < inputs.size(); i += 2) {
            String positionInput = inputs.get(i);
            String instructions = inputs.get(i + 1);
            validateRoverInputs(positionInput, instructions);
            String[] inputSplit = positionInput.split("\\s+");
            Orientation orientation = Orientation.valueOf(inputSplit[2]);
            Position position = new Position(Integer.parseInt(inputSplit[0]), Integer.parseInt(inputSplit[1]),
                    orientation);
            Rover rover = new Rover(position, instructions);
            rovers.add(rover);
        }
    }

    private void navigate() {
        for (Rover rover : rovers) {
            if (!rover.getInstruction().isEmpty() && rover.getInstruction() != null) {
                char[] instructions = rover.getInstruction().toCharArray();
                for (char instruction : instructions) {
                    Instruction instructionValue = Instruction.valueOf(String.valueOf(instruction));
                    switch (instructionValue) {
                        case R:
                        case L:
                            rotate(rover, instructionValue);
                            break;
                        case M:
                            move(rover, rover.getPosition().getOrientation());
                            break;
                    }
                }
            }
        }
    }

    private void printOutput() {
        for (Rover rover : rovers) {
            System.out.println(rover.getPosition().toString());
        }
    }

    private void move(Rover rover, Orientation orientation) {
        Position position = rover.getPosition();
        switch (orientation) {
            case E:
                rover.getPosition().setX(increaseCoordinate(position.getX(), maxX));
                break;
            case W:
                rover.getPosition().setX(decreaseCoordinate(position.getX()));
                break;
            case N:
                rover.getPosition().setY(increaseCoordinate(position.getY(), maxY));
                break;
            case S:
                rover.getPosition().setY(decreaseCoordinate(position.getY()));
                break;
        }
    }

    private int increaseCoordinate(int coordinate, int maxCoordinate) {
        if (coordinate < maxCoordinate) {
            return coordinate + 1;
        }
        throw new RoverNavigatorException("Position out of plateau");
    }

    private int decreaseCoordinate(int coordinate) {
        if (coordinate > 0) {
            return coordinate - 1;
        }
        throw new RoverNavigatorException("Position out of plateau");
    }

    private void rotate(Rover rover, Instruction instruction) {
        rover.getPosition().setOrientation(Orientation.nextOrientation(rover.getPosition().getOrientation(), instruction));
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}