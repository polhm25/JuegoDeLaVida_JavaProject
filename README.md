# Juego de la Vida (Game of Life)

A Java implementation of **Conway's Game of Life**, a cellular automaton simulation. This project is an educational assignment that demonstrates object-oriented programming principles and the classic cellular automaton algorithm.

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [How It Works](#how-it-works)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
  - [Main Menu](#main-menu)
  - [Configuration Options](#configuration-options)
- [Game Rules](#game-rules)
- [Project Structure](#project-structure)
- [Technical Details](#technical-details)
- [Examples](#examples)

## About the Project

This is an implementation of **Conway's Game of Life**, a zero-player game that evolves based on its initial state, requiring no further input. The simulation runs on a 6×6 grid with cells that can be either alive or dead. Each generation, cells evolve according to specific rules based on the number of living neighbors.

This project is an **assignment** created to demonstrate:
- Object-oriented programming (OOP) principles
- Cellular automaton concepts
- Interactive console UI design
- State management and simulation logic

## How It Works

The Game of Life is a cellular automaton where:
1. The grid starts with an initial configuration of live and dead cells
2. Each generation, every cell checks its neighbors
3. Based on neighbor count, cells are born, survive, or die
4. The process repeats for a specified number of generations

This implementation includes:
- A **toroidal topology** (edges wrap around to the opposite side)
- **Cell age tracking** (tracks how many generations each cell has survived)
- **10% spontaneous mutation rate** (cells randomly change state)

## Features

✨ **Multiple Configuration Modes:**
- Predefined pattern (block, blinker, glider, scattered cells)
- Manual custom configuration (place cells by coordinates)
- Random generation (30% fill rate)
- Named patterns (Block, Blinker, Vela, Cross)

⚙️ **Configurable Parameters:**
- Number of generations (1-100)
- Animation speed/delay (100-2000 milliseconds)

📊 **Detailed Information Display:**
- Live cell count per generation
- Text-based grid visualization
- Comprehensive cell registry (position, state, age)

🔄 **Interactive Menu System:**
- User-friendly Spanish-language interface
- Input validation
- Easy navigation between options

## Getting Started

### Prerequisites

- **Java 23** (OpenJDK 23 or later)
- A terminal or command prompt
- No external dependencies required

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/JuegoDeLaVida.git
   cd JuegoDeLaVida
   ```

2. **Compile the Java files:**
   ```bash
   javac -d out/production/JuegoDeLaVida src/*.java
   ```

### Running the Application

```bash
java -cp out/production/JuegoDeLaVida MainGame
```

Or compile and run in one command:
```bash
javac -d out/production/JuegoDeLaVida src/*.java && java -cp out/production/JuegoDeLaVida MainGame
```

## Usage

### Main Menu

When you start the application, you'll see the main menu with 6 options:

```
=====================================
      JUEGO DE LA VIDA
=====================================

1. Ejecutar Simulacion Predefinida      (Run Predefined Simulation)
2. Crear Configuracion Personalizada    (Create Custom Configuration)
3. Generacion Aleatoria                 (Random Generation)
4. Patron Predefinido                   (Select Predefined Pattern)
5. Configurar Parametros                (Configure Parameters)
6. Salir                                (Exit)
```

### Configuration Options

#### 1. Predefined Simulation
Runs a simulation with a default configuration containing:
- A stable 2×2 block
- A 3-cell oscillating blinker
- A moving glider pattern
- Scattered individual cells

#### 2. Custom Configuration
Manually place live cells by entering coordinates (0-5 for both row and column). Enter "listo" (done) when finished.

Example:
```
Ingresa las coordenadas de las celdas vivas
(Las filas y columnas van de 0 a 5)
Escribe 'listo' cuando termines

Fila (0-5) o 'listo': 1
Columna (0-5): 1
Fila (0-5) o 'listo': 1
Columna (0-5): 2
...
Fila (0-5) o 'listo': listo
```

#### 3. Random Generation
Automatically generates a random configuration with approximately 30% of cells alive.

#### 4. Predefined Patterns
Choose from four well-known Game of Life patterns:
- **Bloque** (Block) - A 2×2 stable pattern that never changes
- **Parpadeador** (Blinker) - Oscillates between two states
- **Vela** (Sail/Vela) - A moving pattern
- **Cruz** (Cross) - An interesting multi-generation pattern

#### 5. Configure Parameters
Set simulation parameters:
- **Generations:** 1-100 (how many iterations to run)
- **Delay:** 100-2000 milliseconds (animation speed between generations)

## Game Rules

### Standard Conway's Game of Life

1. **Birth (Nacimiento):** A dead cell with exactly 3 live neighbors becomes alive
2. **Survival (Supervivencia):** A live cell with 2, 3, or 4 neighbors survives
3. **Death (Muerte):** A live cell with fewer than 2 or more than 4 neighbors dies
4. **Stasis:** A dead cell with fewer than 3 neighbors stays dead

### Custom Rules (This Implementation)

**Spontaneous Change:** Each generation, every cell has a 10% probability of randomly toggling its state (alive → dead or dead → alive). This adds unpredictability to the simulation.

**Note:** This implementation uses 2, 3, or 4 neighbors for survival (extended from the classic 2-3 rule).

### Cell Age

- Newly born cells have age 1
- Surviving cells increment their age each generation
- Dead cells have age 0

## Project Structure

```
JuegoDeLaVida/
├── src/
│   ├── MainGame.java    # Application entry point and main loop
│   ├── Menu.java        # User interface and menu system
│   ├── Board.java       # Game state and logic (6x6 grid)
│   └── Cell.java        # Individual cell representation
├── out/                 # Compiled bytecode (generated)
├── .gitignore          # Git ignore rules
└── README.md           # This file
```

### Class Descriptions

| Class | Purpose |
|-------|---------|
| **MainGame** | Entry point; manages main menu loop and dispatches user actions |
| **Menu** | Handles user interface, input validation, and simulation execution |
| **Board** | Manages the 6×6 grid; implements Game of Life rules and generation evolution |
| **Cell** | Represents a single cell; tracks alive/dead state and age |

## Technical Details

### Grid Size
6×6 cells (36 total cells)

### Topology
**Toroidal (Wrapping):** The grid wraps around at edges. A cell at the bottom has a neighbor at the top, and a cell at the right has a neighbor at the left.

### Implementation Highlights
- Atomic updates using a temporary grid to avoid conflicts during generation updates
- Modulo arithmetic for toroidal wrapping
- Input validation for all user entries
- Console-based visualization using '1' for alive and '0' for dead cells

## Examples

### Example 1: Running the Predefined Simulation

```
$ java -cp out/production/JuegoDeLaVida MainGame
=====================================
      JUEGO DE LA VIDA
=====================================

1. Ejecutar Simulacion Predefinida
2. Crear Configuracion Personalizada
3. Generacion Aleatoria
4. Patron Predefinido
5. Configurar Parametros
6. Salir

=====================================
Selecciona una opcion (1-6): 1

Iniciando simulacion con 20 generaciones...

Generation 1, Vivas: 12
1 1 0 0 0 0
1 1 0 0 1 0
0 0 0 1 1 0
1 0 0 0 0 0
0 0 0 0 0 0
1 1 0 0 0 0
```

### Example 2: Creating a Custom Configuration

```
Selecciona una opcion (1-6): 2

=====================================
      CONFIGURACION PERSONALIZADA
=====================================

Ingresa las coordenadas de las celdas vivas
(Las filas y columnas van de 0 a 5)
Escribe 'listo' cuando termines

Fila (0-5) o 'listo': 2
Columna (0-5): 2
Fila (0-5) o 'listo': 2
Columna (0-5): 3
Fila (0-5) o 'listo': 2
Columna (0-5): 4
Fila (0-5) o 'listo': listo

Iniciando simulacion con 20 generaciones...
```

## Language Note

This project uses **Spanish** for its interface and code comments. The primary language for variable names and user-facing text is Spanish, reflecting the original assignment context.

---

## License

This project is provided as-is for educational purposes.

---

**Enjoy the Game of Life! 🧬**
