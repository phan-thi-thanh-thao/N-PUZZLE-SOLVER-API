package com.npuzzle.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PuzzleRequestTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        int[][] board = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };

        // Act
        PuzzleRequest request = new PuzzleRequest();
        request.setBoard(board);

        // Assert
        assertArrayEquals(board, request.getBoard());
    }

    @Test
    public void testSetters() {
        // Arrange
        PuzzleRequest request = new PuzzleRequest();
        int[][] newBoard = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 5, 8}
        };

        // Act
        request.setBoard(newBoard);

        // Assert
        assertArrayEquals(newBoard, request.getBoard());
    }
} 