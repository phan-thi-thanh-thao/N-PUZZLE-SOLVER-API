package com.npuzzle.controller;

import com.npuzzle.model.PuzzleRequest;
import com.npuzzle.model.PuzzleResult;
import com.npuzzle.solver.NPuzzleSolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PuzzleController {

    @PostMapping("/solve")
    public ResponseEntity<?> solvePuzzle(@RequestBody PuzzleRequest request) {
        NPuzzleSolver solver = new NPuzzleSolver();
        try {
            PuzzleResult result = solver.solve(request.getBoard());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}