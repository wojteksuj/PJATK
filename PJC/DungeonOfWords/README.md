# Dungeon of Words

**Dungeon of Words** is a 2D word-based arcade game developed in C++ using the **SFML (Simple and Fast Multimedia Library)**.

## Description

Dungeon of Words challenges players to type falling words correctly before they reach the bottom of the screen. Players must respond quickly to maintain their score and health, represented visually by heart icons.

## Features

- **Multiple Game States**:
  - Menu
  - Options
  - Gameplay
  - Pause
  - Game Over
  - Scoreboard
  - Saving

- **Gameplay Mechanics**:
  - Words fall with velocity and must be typed correctly.
  - Score increases with each correct word.
  - Health decreases when words are missed.
  - Game over triggers when all hearts are lost.

- **Customization**:
  - Difficulty adjustment via sliders.
  - Font selection options.
  - Textures and fonts loaded from external files.

- **Scoreboard**:
  - Top scorers are read from and written to a `scores.txt` file.
  - Best five scores are displayed on the scoreboard.

- **Graphics and UI**:
  - SFML rendering for all game elements including text, backgrounds, sliders, and icons.
  - Custom textures for walls and hearts.
  - Various fonts for UI styling.

## Technical Highlights

- Built with SFML for windowing, graphics, and event handling.
- Modular code with separate files for rendering, game logic, and score management.
- External resource loading (fonts, textures, word lists).
- Efficient rendering loop and game state management.

---

*Developed as a C++ SFML project for interactive word-based gameplay.*
