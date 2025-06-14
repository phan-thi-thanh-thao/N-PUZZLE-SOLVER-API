# N-Puzzle Solver API: Efficiently Solve Sliding Tile Puzzles with RESTful Endpoints

A Spring Boot-based REST API that solves the classic N-Puzzle sliding tile problem using an optimized breadth-first search algorithm with Manhattan distance heuristics. The API provides a simple interface to solve 3x3 sliding tile puzzles while ensuring optimal solutions.

This project implements a robust solver for the N-Puzzle problem, where tiles numbered from 1 to 8 must be arranged in order with an empty space. The solver uses an efficient breadth-first search algorithm enhanced with Manhattan distance heuristics to find the shortest possible solution path. The API is built using Spring Boot and follows RESTful principles, making it easy to integrate into any application that needs puzzle-solving capabilities.

## Repository Structure
```
.
├── pom.xml                 # Maven project configuration and dependencies
├── src
│   ├── main
│   │   ├── java/com/npuzzle
│   │   │   ├── Application.java              # Spring Boot application entry point
│   │   │   ├── config
│   │   │   │   └── WebConfig.java           # CORS configuration
│   │   │   ├── controller
│   │   │   │   └── PuzzleController.java    # REST endpoint definitions
│   │   │   ├── model
│   │   │   │   ├── PuzzleRequest.java       # Input request model
│   │   │   │   └── PuzzleResult.java        # Solution response model
│   │   │   └── solver
│   │   │       └── NPuzzleSolver.java       # Core puzzle solving algorithm
│   │   └── resources
│   │       ├── application.properties        # Application configuration
│   │       └── static
│   │           └── index.html               # Web interface for testing
│   └── test                                 # Test suite directory
```

## Usage Instructions
### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 2.x compatible environment

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd n-puzzle-api
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

### Quick Start

1. Start the server and verify it's running:
```bash
curl http://localhost:8080/
```

2. Send a puzzle solving request:
```bash
curl -X POST http://localhost:8080/api/solve \
  -H "Content-Type: application/json" \
  -d '{
    "board": [
      [1, 2, 3],
      [4, 0, 6],
      [7, 5, 8]
    ]
  }'
```

### More Detailed Examples

1. Solve a puzzle one move away from solution:
```java
PuzzleRequest request = new PuzzleRequest();
request.setBoard(new int[][]{
    {1, 2, 3},
    {4, 5, 6},
    {7, 0, 8}
});
```

2. Handle an unsolvable puzzle:
```java
try {
    ResponseEntity<PuzzleResult> response = puzzleController.solvePuzzle(request);
} catch (IllegalArgumentException e) {
    System.out.println("Puzzle is unsolvable: " + e.getMessage());
}
```

### Troubleshooting

Common Issues:

1. Invalid Board Configuration
```
Error: "Board must contain number 0"
Solution: Ensure the input board contains exactly one empty space represented by 0
```

2. Wrong Board Size
```
Error: "Invalid board size"
Solution: The board must be exactly 3x3 in size
```

3. Performance Issues
- Enable debug logging by adding to application.properties:
```
logging.level.com.npuzzle=DEBUG
```
- Monitor solver performance:
```
logging.level.com.npuzzle.solver=TRACE
```

## Data Flow

The N-Puzzle solver processes puzzle requests through a pipeline that validates input, applies the solving algorithm, and returns the solution path.

```ascii
[Client] -> HTTP POST /api/solve 
            -> [PuzzleController] 
               -> [NPuzzleSolver] 
                  -> [BFS Algorithm] 
                     -> [Solution Path] 
                        -> [Client]
```

Key component interactions:
1. Client sends puzzle board configuration via REST API
2. PuzzleController validates input and formats request
3. NPuzzleSolver applies BFS with Manhattan distance heuristic
4. Solution path is constructed from goal state to initial state
5. Response includes complete move sequence and step count
6. CORS configuration enables cross-origin requests
7. Error handling provides clear feedback for invalid inputs
8. Test coverage ensures reliable operation"# N-PUZZLE-SOLVER-API" 
