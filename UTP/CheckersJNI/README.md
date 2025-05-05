## ♟️ Checkers Board

**Checkers Board** is a 2D interactive checkers (draughts) game developed in **Java**, with the core game logic implemented in **C** and accessed via **JNI (Java Native Interface)** for performance and rule accuracy.

---

### 📝 Description

The game offers a classic checkers experience through a Swing-based GUI. Core rules — including piece movement, multi-captures, and king promotions — are enforced by a native C backend, ensuring both speed and rule correctness. The GUI provides visual feedback and intuitive interaction for players.

---

### ✨ Features

- 🖼️ **Java Swing GUI:** Clickable 8×8 board with visual indicators for legal moves and captures.
- ⚙️ **C Backend via JNI:** Efficient and robust rule enforcement, including:
  - Regular and king (dame) piece logic
  - Forced captures and multi-jumps
- 👑 **Gameplay Rules:**
  - Automatic promotion to king
  - Multi-capture chaining
  - Visual separation between regular and king pieces
- 🧪 **JUnit Test Suite:** Covers game logic scenarios like valid moves, promotions, and edge cases.
- 🧩 **Modular Architecture:** Clean separation between GUI, logic, and native integration.

---

### 🗂️ Project Structure

- `Board.java` – Handles GUI, user actions, and JNI calls.
- `Board.h` – JNI header defining native method bindings.
- `Main.java` – Launches the game window.
- `MyFrame.java` – Custom `JFrame` for displaying the board.
- `MainTest.java` – JUnit tests for rules and logic validation.

---

### 🚀 Getting Started

1. **Build the Native Library:**
   - Compile the C code to a shared library named `Project1_C`.
   - Place it where your Java app can load it (e.g., `java.library.path`).

2. **Run the Game:**
   - Launch `Main.java` to start the checkers application.

3. **Play:**
   - Click tiles to select and move.
   - Game automatically handles turn-based play, captures, and king promotions.

---

### ✅ Requirements

- Java 8+
- Native C compiler
- JUnit (for testing)

---

🛠️ *Developed as part of the UTP (Universal Programming Techniques) course to demonstrate Java–C integration with interactive gameplay.*
