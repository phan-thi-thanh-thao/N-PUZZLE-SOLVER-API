package com.npuzzle.solver;

import com.npuzzle.model.PuzzleResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class NPuzzleSolverTest {

    private final NPuzzleSolver solver = new NPuzzleSolver();

    @Test
    public void testAlreadySolvedPuzzle() {
        // TC-001: Kiểm tra board đã giải
        PuzzleResult result = solver.solve(TestDataProvider.GOAL_STATE);
        assertNotNull(result);
        assertEquals(0, result.getSteps());
        assertFalse(result.getSolution().isEmpty());
        assertArrayEquals(TestDataProvider.GOAL_STATE, result.getSolution().get(0));
    }

    @Test
    public void testOneMoveAway() {
        // TC-002: Kiểm tra board cần 1 bước
        PuzzleResult result = solver.solve(TestDataProvider.ONE_MOVE_AWAY);
        assertNotNull(result);
        assertEquals(1, result.getSteps());
        assertEquals(2, result.getSolution().size());
    }

    @Test
    public void testTwoMovesAway() {
        // TC-003: Kiểm tra board cần 2 bước
        PuzzleResult result = solver.solve(TestDataProvider.TWO_MOVES_AWAY);
        assertNotNull(result);
        assertEquals(2, result.getSteps());
        assertEquals(3, result.getSolution().size());
    }

    @Test
    public void testComplexPuzzle() {
        // TC-004: Kiểm tra board phức tạp
        PuzzleResult result = solver.solve(TestDataProvider.COMPLEX_PUZZLE);
        assertNotNull(result);
        assertTrue(result.getSteps() > 0);
        assertTrue(result.getSolution().size() > 1);
        
        // Kiểm tra mỗi bước di chuyển là hợp lệ
        List<int[][]> solution = result.getSolution();
        for (int i = 0; i < solution.size() - 1; i++) {
            assertTrue(isValidMove(solution.get(i), solution.get(i + 1)));
        }
    }

    @Test
    public void testEmptyTilePositions() {
        // TC-005: Kiểm tra các vị trí khác nhau của ô trống
        int[][][] testBoards = {
            TestDataProvider.EMPTY_CORNER_TOP_LEFT,
            TestDataProvider.EMPTY_CORNER_TOP_RIGHT,
            TestDataProvider.EMPTY_CORNER_BOTTOM_LEFT,
            TestDataProvider.EMPTY_CORNER_BOTTOM_RIGHT,
            TestDataProvider.EMPTY_EDGE_TOP,
            TestDataProvider.EMPTY_EDGE_LEFT,
            TestDataProvider.EMPTY_EDGE_RIGHT,
            TestDataProvider.EMPTY_EDGE_BOTTOM,
            TestDataProvider.EMPTY_CENTER
        };

        for (int[][] board : testBoards) {
            PuzzleResult result = solver.solve(board);
            assertNotNull(result);
            assertTrue(result.getSteps() >= 0);
            assertFalse(result.getSolution().isEmpty());
            
            // Kiểm tra trạng thái cuối cùng là goal state
            int[][] lastState = result.getSolution().get(result.getSolution().size() - 1);
            assertArrayEquals(TestDataProvider.GOAL_STATE, lastState);
        }
    }

    @Test
    public void testGeneratedBoards() {
        // TC-006: Kiểm tra các board được tạo động
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[][] board = TestDataProvider.createBoardWithEmptyAt(i, j);
                PuzzleResult result = solver.solve(board);
                assertNotNull(result);
                assertTrue(result.getSteps() >= 0);
                assertFalse(result.getSolution().isEmpty());
                
                // Kiểm tra trạng thái cuối cùng là goal state
                int[][] lastState = result.getSolution().get(result.getSolution().size() - 1);
                assertArrayEquals(TestDataProvider.GOAL_STATE, lastState);
            }
        }
    }

    @Test
    public void testInvalidBoard() {
        // TC-007: Kiểm tra board không hợp lệ (không có số 0)
        int[][] invalidBoard = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        assertThrows(IllegalArgumentException.class, () -> solver.solve(invalidBoard));
    }

    @Test
    public void testWrongSizeBoard() {
        // TC-008: Kiểm tra board sai kích thước
        int[][] wrongSizeBoard = {
            {1, 2, 3},
            {4, 5, 6}
        };
        assertThrows(IllegalArgumentException.class, () -> solver.solve(wrongSizeBoard));
    }

    @Test
    public void testDuplicateNumbersBoard() {
        // TC-009: Kiểm tra board có số trùng lặp
        int[][] duplicateBoard = {
            {1, 2, 3},
            {4, 5, 5},
            {7, 8, 0}
        };
        assertThrows(IllegalArgumentException.class, () -> solver.solve(duplicateBoard));
    }

    private boolean isValidMove(int[][] current, int[][] next) {
        int[] zeroPos = findZero(current);
        int[] nextZeroPos = findZero(next);
        
        // Kiểm tra di chuyển hợp lệ (chỉ 1 ô theo chiều ngang hoặc dọc)
        int dx = Math.abs(zeroPos[0] - nextZeroPos[0]);
        int dy = Math.abs(zeroPos[1] - nextZeroPos[1]);
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    private int[] findZero(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
} 