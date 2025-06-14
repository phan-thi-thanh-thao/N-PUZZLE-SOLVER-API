package com.npuzzle.model;

import java.util.List;

public class PuzzleResult {
    private List<int[][]> solution;
    private int steps;

    public PuzzleResult(List<int[][]> solution, int steps) {
        this.solution = solution;
        this.steps = steps;
    }

    public List<int[][]> getSolution() {
        return solution;
    }

    public void setSolution(List<int[][]> solution) {
        this.solution = solution;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}