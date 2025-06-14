package com.npuzzle.controller;

import com.npuzzle.model.PuzzleRequest;
import com.npuzzle.model.PuzzleResult;
import com.npuzzle.solver.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpStatus;

public class PuzzleControllerTest {

    private final PuzzleController controller = new PuzzleController();

    @Test
    public void testSolvePuzzle() {
        // TC-001: Kiểm tra board thông thường
        PuzzleRequest request = new PuzzleRequest();
        request.setBoard(TestDataProvider.EMPTY_CENTER);

        ResponseEntity<?> response = controller.solvePuzzle(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PuzzleResult);
        
        PuzzleResult result = (PuzzleResult) response.getBody();
        assertNotNull(result);
        assertTrue(result.getSteps() > 0);
        assertFalse(result.getSolution().isEmpty());
    }

    @Test
    public void testSolvePuzzleWithAlreadySolvedBoard() {
        // TC-002: Kiểm tra board đã giải
        PuzzleRequest request = new PuzzleRequest();
        request.setBoard(TestDataProvider.GOAL_STATE);

        ResponseEntity<?> response = controller.solvePuzzle(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PuzzleResult);
        
        PuzzleResult result = (PuzzleResult) response.getBody();
        assertNotNull(result);
        assertEquals(0, result.getSteps());
        assertFalse(result.getSolution().isEmpty());
    }

    @Test
    public void testSolvePuzzleWithOneMoveAway() {
        // TC-003: Kiểm tra board cần 1 bước
        PuzzleRequest request = new PuzzleRequest();
        request.setBoard(TestDataProvider.ONE_MOVE_AWAY);

        ResponseEntity<?> response = controller.solvePuzzle(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PuzzleResult);
        
        PuzzleResult result = (PuzzleResult) response.getBody();
        assertNotNull(result);
        assertEquals(1, result.getSteps());
        assertEquals(2, result.getSolution().size());
    }

    @Test
    public void testSolvePuzzleWithInvalidBoard() {
        // TC-004: Kiểm tra board không hợp lệ
        PuzzleRequest request = new PuzzleRequest();
        int[][] invalidBoard = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}  // Không có số 0
        };
        request.setBoard(invalidBoard);

        ResponseEntity<?> response = controller.solvePuzzle(request);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testSolvePuzzleWithWrongSizeBoard() {
        // TC-005: Kiểm tra board sai kích thước
        PuzzleRequest request = new PuzzleRequest();
        int[][] wrongSizeBoard = {
            {1, 2, 3},
            {4, 5, 6}
        };
        request.setBoard(wrongSizeBoard);

        ResponseEntity<?> response = controller.solvePuzzle(request);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testSolvePuzzleWithInvalidBoardReturnsBadRequest() {
        // Given
        int[][] invalidBoard = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}  // Board không có số 0
        };
        PuzzleRequest request = new PuzzleRequest();
        request.setBoard(invalidBoard);

        // When
        ResponseEntity<?> response = controller.solvePuzzle(request);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid board size", response.getBody());
    }
} 