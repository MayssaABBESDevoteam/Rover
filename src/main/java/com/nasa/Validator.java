package com.nasa;

import com.nasa.exception.RoverNavigatorException;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    public static void validateMaxCoordinate(String maxCoordinateLine) {
        if (!Pattern.matches("^\\d\\s\\d$", maxCoordinateLine)) {
            throw new RoverNavigatorException(String.format("MaxCoordinate not valid: %s", maxCoordinateLine));
        }
    }


    public static void validateInputLinesNumber(List<String> inputs) {
        //This method permit to check the input lines number
        // The input is invalid if the lines number is pair
        if (inputs.size() % 2 != 1) {
            throw new RoverNavigatorException("Invalid input!");
        }
    }

    public static void validateRoverInputs(String roverPosition, String instruction) {
        validateRoverPosition(roverPosition);
        validateInstructions(instruction);
    }

    private static void validateRoverPosition(String roverPosition) {
        if (!Pattern.matches("^\\d\\s\\d\\s[NSEW]$", roverPosition)) {
            throw new RoverNavigatorException(String.format("Rover Position not valid: %s", roverPosition));
        }
    }

    private static void validateInstructions(String instructions) {
        if (!Pattern.matches("^[RML]+$", instructions)) {
            throw new RoverNavigatorException(String.format("Rover Instructions not valid: %s", instructions));
        }
    }

}
