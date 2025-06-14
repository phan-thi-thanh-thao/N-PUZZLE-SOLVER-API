package com.npuzzle.solver;

import com.npuzzle.model.PuzzleResult;
import java.util.*;

public class NPuzzleSolver {

    private static final int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private record Node(int[][] board, int cost, int depth, Node parent) {}

    public PuzzleResult solve(int[][] board) {
        // Kiểm tra kích thước board
        if (board == null || board.length != 3 || board[0].length != 3) {
            throw new IllegalArgumentException("Board must be 3x3");
        }

        // Kiểm tra board có số 0 không
        boolean hasZero = false;
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    hasZero = true;
                }
                numbers.add(board[i][j]);
            }
        }
        if (!hasZero) {
            throw new IllegalArgumentException("Board must contain number 0");
        }
        if (numbers.size() != 9) {
            throw new IllegalArgumentException("Board must contain unique numbers from 0 to 8");
        }

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost + n.depth));
        Set<String> visited = new HashSet<>();

        Node start = new Node(board, heuristic(board), 0, null);
        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();
            if (Arrays.deepEquals(current.board, goal)) {
                List<int[][]> path = reconstructPath(current);
                return new PuzzleResult(path, path.size() - 1);
            }

            String hash = Arrays.deepToString(current.board);
            if (visited.contains(hash)) continue;
            visited.add(hash);

            int[] zero = findZero(current.board);
            int zx = zero[0], zy = zero[1];

            for (int i = 0; i < 4; i++) {
                int nx = zx + dx[i], ny = zy + dy[i];
                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    int[][] newBoard = copy(current.board);
                    newBoard[zx][zy] = newBoard[nx][ny];
                    newBoard[nx][ny] = 0;
                    if (!visited.contains(Arrays.deepToString(newBoard))) {
                        open.add(new Node(newBoard, heuristic(newBoard), current.depth + 1, current));
                    }
                }
            }
        }
        return new PuzzleResult(new ArrayList<>(), 0);
    }

    private int heuristic(int[][] board) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 0) {
                    int val = board[i][j] - 1;
                    h += Math.abs(i - val / 3) + Math.abs(j - val % 3);
                }
            }
        }
        return h;
    }

    private int[][] copy(int[][] board) {
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) newBoard[i] = board[i].clone();
        return newBoard;
    }

    private int[] findZero(int[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == 0)
                    return new int[]{i, j};
        return null;
    }

    private List<int[][]> reconstructPath(Node node) {
        LinkedList<int[][]> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.board);
            node = node.parent;
        }
        return path;
    }
}