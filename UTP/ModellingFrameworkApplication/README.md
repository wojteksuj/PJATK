# Modelling Framework Sample

**Modelling Framework Sample** is a Java-based desktop application for running, extending, and analyzing economic models using data files and Groovy scripting. The application features a modular design, a Swing-based GUI, and supports dynamic model extension with custom scripts.

## Description

This tool enables users to select from multiple predefined economic models, load time series data, run simulations, and analyze results-all through an interactive graphical interface. Users can also extend model outputs by writing and executing Groovy scripts, either loaded from files or written directly in the built-in script editor. Results are presented in a tabular format for easy analysis.

## Features

- **Multiple Economic Models:**  
  Choose from predefined models (`Model1`, `Model2`, `Model3`), each with different economic logic and outputs.

- **Data-Driven Simulation:**  
  Load data files (e.g., `Data1.txt`, `Data2.txt`, `Data3.txt`) to initialize model variables and time series.

- **Groovy Script Integration:**  
  - Run scripts from files or write and execute scripts in the built-in editor.
  - Scripts can create new variables or transform results dynamically.

- **Interactive GUI:**  
  - Model and data selection via lists.
  - Buttons for running models, running scripts, and opening the script editor.
  - Results displayed in a scrollable, formatted text area.

- **Extensible Design:**  
  - Easily add new models by subclassing the `Model` class and annotating fields with `@Bind`.
  - Add new data files or scripts without recompiling the application.

## Project Structure

- `GUI.java` – Main application window and layout.
- `LeftPanel.java` – Model/data selection, action buttons, and script editor.
- `RightPanel.java` – Output display area for results.
- `Controller.java` – Orchestrates model loading, data parsing, running models, and script execution.
- `Model1.java`, `Model2.java`, `Model3.java` – Example economic models.
- `Bind.java` – Annotation for model variable binding.
- `script1.groovy` – Example Groovy script for post-processing model results.

## Getting Started

1. **Run the application:**  
   Compile and launch `GUI.java` to open the main window.

2. **Select a model and data file:**  
   Use the left panel to choose a model and a data file.

3. **Run a model:**  
   Click "Run model" to execute the selected model with the chosen data. Results appear on the right.

4. **Run or create scripts:**  
   - Use "Run script from file" to select and execute a Groovy script.
   - Use "Create and run script" to open the script editor, write custom Groovy code, and run it on the model output.

## Requirements

- Java 8 or higher
- Groovy library (for scripting support)
- Data files in tabular format

---

*Developed as a modular Java Swing application for interactive economic modelling and analysis.*
