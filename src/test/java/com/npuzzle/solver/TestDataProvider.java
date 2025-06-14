package com.npuzzle.solver;

public class TestDataProvider {
    // Goal state
    public static final int[][] GOAL_STATE = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    // Test cases for different positions of empty tile
    public static final int[][] EMPTY_CORNER_TOP_LEFT = {
        {0, 1, 3},
        {4, 2, 5},
        {7, 8, 6}
    };

    public static final int[][] EMPTY_CORNER_TOP_RIGHT = {
        {1, 3, 0},
        {4, 2, 5},
        {7, 8, 6}
    };

    public static final int[][] EMPTY_CORNER_BOTTOM_LEFT = {
        {1, 2, 3},
        {4, 5, 6},
        {0, 7, 8}
    };

    public static final int[][] EMPTY_CORNER_BOTTOM_RIGHT = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    public static final int[][] EMPTY_EDGE_TOP = {
        {1, 0, 3},
        {4, 2, 5},
        {7, 8, 6}
    };

    public static final int[][] EMPTY_EDGE_LEFT = {
        {1, 2, 3},
        {0, 4, 5},
        {7, 8, 6}
    };

    public static final int[][] EMPTY_EDGE_RIGHT = {
        {1, 2, 3},
        {4, 5, 0},
        {7, 8, 6}
    };

    public static final int[][] EMPTY_EDGE_BOTTOM = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 0, 8}
    };

    public static final int[][] EMPTY_CENTER = {
        {1, 2, 3},
        {4, 0, 5},
        {7, 8, 6}
    };

    // Test cases for different move sequences
    public static final int[][] ONE_MOVE_AWAY = {
        {1, 2, 3},
        {4, 5, 0},
        {7, 8, 6}
    };

    public static final int[][] TWO_MOVES_AWAY = {
        {1, 2, 3},
        {4, 0, 5},
        {7, 8, 6}
    };

    public static final int[][] COMPLEX_PUZZLE = {
        {0, 1, 3},
        {4, 2, 5},
        {7, 8, 6}
    };

    // Helper method to create a board with empty tile at specific position
    public static int[][] createBoardWithEmptyAt(int row, int col) {
        int[][] board = new int[3][3];
        int value = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == row && j == col) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = value++;
                }
            }
        }
        return board;
    }
} 