package com.npuzzle.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleResultTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        List<int[][]> solution = new ArrayList<>();
        int steps = 5;

        // Act
        PuzzleResult result = new PuzzleResult(solution, steps);

        // Assert
        assertEquals(solution, result.getSolution());
        assertEquals(steps, result.getSteps());
    }

    @Test
    public void testSetters() {
        // Arrange
        PuzzleResult result = new PuzzleResult(new ArrayList<>(), 0);
        List<int[][]> newSolution = new ArrayList<>();
        int newSteps = 10;

        // Act
        result.setSolution(newSolution);
        result.setSteps(newSteps);

        // Assert
        assertEquals(newSolution, result.getSolution());
        assertEquals(newSteps, result.getSteps());
    }
} 