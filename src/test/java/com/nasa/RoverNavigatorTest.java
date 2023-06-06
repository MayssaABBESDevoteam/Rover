package com.nasa;

import com.nasa.model.Orientation;
import com.nasa.model.Position;
import com.nasa.model.Rover;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class RoverNavigatorTest {
    private RoverNavigator roverNavigator;
    private static final String FILE_NAME = "input.txt";
    private static final int EXPECTED_MAX_X = 5;
    private static final int EXPECTED_MAX_Y = 5;
    private static final String UPPER_RIGHT_COORDINATES = "5 5";
    private static final String ROVER_INPUT_POSITION = "1 2 N";
    private static final String ROVER_INPUT_INSTRUCTIONS = "LMLMLMLMM";
    private Rover expectedRover;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        String fileAbsolutePath = file.getAbsolutePath();
        Position expectedPosition = new Position(1, 3, Orientation.N);
        expectedRover = new Rover(expectedPosition, ROVER_INPUT_INSTRUCTIONS);
        System.setOut(new PrintStream(outputStreamCaptor));

        roverNavigator = new RoverNavigator();
        roverNavigator.execute(fileAbsolutePath);
    }

    @Test
    public void should_extract_input() {
        List<String> result = roverNavigator.getInputs();
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(UPPER_RIGHT_COORDINATES, result.get(0));
        assertEquals(ROVER_INPUT_POSITION, result.get(1));
        assertEquals(ROVER_INPUT_INSTRUCTIONS, result.get(2));
    }

    @Test
    public void should_extract_max_coordinate() {
        assertEquals(EXPECTED_MAX_X, roverNavigator.getMaxX());
        assertEquals(EXPECTED_MAX_Y, roverNavigator.getMaxY());
    }

    @Test
    public void should_init_rovers() {
        List<Rover> result = roverNavigator.getRovers();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNotNull(result.get(0).getInstruction());
        assertEquals(ROVER_INPUT_INSTRUCTIONS, result.get(0).getInstruction());
    }

    @Test
    public void should_navigate() {
        List<Rover> result = roverNavigator.getRovers();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNotNull(result.get(0).getPosition());
        assertEquals(expectedRover.getPosition().toString(), result.get(0).getPosition().toString());
    }

    @Test
    public void should_print_output() {
        Assert.assertEquals("1 3 N", outputStreamCaptor.toString()
                .trim());
    }
}