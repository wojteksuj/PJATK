## 📊 Modelling Framework Sample

**Modelling Framework Sample** is a Java-based desktop application designed for running, extending, and analyzing economic models using time-series data and dynamic Groovy scripting.

---

### 📝 Description

This tool enables users to choose predefined economic models, load time-series data, simulate results, and dynamically extend model behavior using Groovy scripts — all through an interactive graphical interface. Output is presented in a structured tabular format for analysis.

---

### ✨ Features

- 🧠 **Multiple Economic Models**  
  Select from built-in models like `Model1`, `Model2`, and `Model3`, each with distinct economic logic.

- 📂 **Data-Driven Simulation**  
  Initialize variables and simulate time-series using data files (e.g., `Data1.txt`, `Data2.txt`, etc.).

- 💡 **Groovy Script Integration**  
  - Load and execute external scripts.
  - Write custom Groovy scripts directly in the in-app editor to transform model output or generate new variables.

- 🖥️ **Interactive Swing GUI**  
  - Left panel: Model/data selection, action buttons, and script editor.
  - Right panel: Formatted scrollable area for displaying results.

- 🧩 **Extensible Architecture**  
  - Add new models by subclassing `Model` and annotating variables with `@Bind`.
  - Easily add scripts and data without modifying source code.

---

### 🗂️ Project Structure

- `GUI.java` – Launches the main application window.
- `LeftPanel.java` – Model/data selection UI, buttons, and script editor.
- `RightPanel.java` – Displays results in a formatted view.
- `Controller.java` – Manages the workflow between UI, model, data, and scripts.
- `Model1.java`, `Model2.java`, `Model3.java` – Sample economic models.
- `Bind.java` – Annotation for binding model variables.
- `script1.groovy` – Example script for data transformation.

---

### 🚀 Getting Started

1. **Launch the Application:**  
   Compile and run `GUI.java`.

2. **Choose a Model & Data File:**  
   Use the left panel to select a model and input data.

3. **Run the Model:**  
   Click **Run Model** to simulate using the loaded data. View results on the right.

4. **Run or Write Scripts:**  
   - Use **Run Script from File** to execute saved Groovy scripts.
   - Use **Create and Run Script** to open the built-in script editor and test code on-the-fly.

---

### ✅ Requirements

- Java 8+
- Groovy (for script execution)
- Data files in tab-separated or similar tabular format

---

🛠️ *Developed as part of the UTP (Universal Programming Techniques) course to demonstrate dynamic modeling, GUI design, and scripting integration.*
