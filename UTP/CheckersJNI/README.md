# Checkers Board

**Checkers Board** is a 2D checkers (draughts) game built in Java, featuring a graphical 8x8 board and advanced game logic powered by a native C backend via JNI.

## Description

Checkers Board provides a classic checkers experience with an interactive GUI. The game logic-including piece movement, capturing, multi-capturing, and promotion to "dame" (king)-is handled by a performant C library, ensuring accurate rules and smooth gameplay. The Java front-end uses Swing to render the board and handle user interaction.

## Features

- **Interactive Java Swing GUI**: Clickable 8x8 board with visual feedback for moves and captures.
- **Native C Backend**: Board state and rules are managed in C, accessed from Java via JNI for efficiency and reliability.
- **Standard Checkers Gameplay**:
  - Piece movement and capturing, including forced and multi-captures.
  - Promotion to "dame" (king) pieces with special movement rules.
  - Visual distinction for regular and king pieces.
- **Comprehensive Testing**: JUnit tests cover board setup, valid moves, capturing, promotion, and multi-capture scenarios.
- **Modular Design**: Clear separation between UI, game logic, and native integration.

## Project Structure

- `Board.java` – Main game logic, JNI bindings, board rendering, and user interaction.
- `Board.h` – JNI header for native methods, defines C interface.
- `Main.java` – Application entry point; launches the game window.
- `MyFrame.java` – Custom JFrame for displaying the checkers board.
- `MainTest.java` – JUnit test suite for board logic and gameplay rules.

## Getting Started

1. **Build the Native C Library**  
   Compile the C code as a shared library named `Project1_C` and ensure it is accessible to Java.

2. **Run the Java Application**  
   Launch `Main.java` to start the game window.

3. **Play**  
   Click on board tiles to select and move pieces. The game enforces standard checkers rules, including capturing and promotion.

## Requirements

- Java 8 or higher
- Native C compiler (for JNI library)
- JUnit (for running tests)

---

*Developed as a Java/C JNI project for interactive checkers gameplay.*
