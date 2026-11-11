# Vacuum Cleaner Agent Simulation

A Java-based simulation of an autonomous vacuum cleaner developed as a project for an Artificial Intelligence course. The program creates a random grid environment and uses an agent to visit the cells, clean dirty locations, and track its exploration.

## How it works

1. The user enters the number of rows and columns (at least 80) and the starting cell coordinates.
2. The environment is initialized randomly: each cell is either clean or dirty.
3. The agent identifies the region containing its starting position and moves toward the corresponding corner of the grid.
4. From that corner, it follows a back-and-forth, row-by-row (serpentine) traversal pattern.
5. When the agent visits a dirty cell, it cleans it. A separate visit map records which cells have been explored.
6. The simulation ends when every cell has been visited.

After each move, the program displays the environment, the visit map, the agent's location, and the current percept.

## Run locally

You need a Java Development Kit (JDK).

Compile the program:

```bash
javac ai_project1.java
```

Run it:

```bash
java ai_project1
```

When prompted, enter:
- The number of rows and columns (each at least 80).
- The starting row and column, using zero-based indices within the grid.

## Implementation

- **Language:** Java
- **Environment:** A randomly generated two-dimensional grid
- **Agent behavior:** Percept-based cleaning and serpentine exploration
- **State tracking:** Separate arrays for the environment and visited cells

## Project context

This project was developed as an Artificial Intelligence course assignment and demonstrates a simple agent interacting with a grid-based environment through movement, perception, and cleaning actions.
