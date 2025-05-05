## 📡 Project: Distributed Averaging System (DAS)

### 🧾 Introduction

The **Distributed Averaging System (DAS)** is a Java-based project demonstrating fundamental concepts of distributed computing and message passing over **UDP sockets**. Each process can act as either a **master** or **slave**, depending on the availability of a specific UDP port. The system showcases real-time communication, data aggregation, and broadcasting in a networked environment.

---

### ✨ Key Features

- 🔁 **Automatic Role Selection** (Master/Slave based on port availability)
- 🧠 **Master Mode**: Data collection, averaging, and broadcasting
- 📤 **Slave Mode**: Sends a number and exits
- 🌐 **UDP-based Communication** across local network
- 📊 **Distributed Averaging Algorithm** with live coordination

---

### 🧭 Mode Descriptions

#### 🧑‍✈️ Master Mode

The application enters master mode when it can successfully bind to the specified UDP port. In this mode, the master:

- Accepts numbers from slaves via UDP.
- Stores the numbers in memory for aggregation.
- Treats the **first** number as its own input.
- Reacts to specific numbers:
  - `0` → Calculates and **broadcasts the average**.
  - `-1` → **Sends a termination signal** (`-1`) to all clients and shuts down.
  - Any other number → Logs and stores it.

#### 🧑‍💻 Slave Mode

If the port is already taken (i.e., a master is running), the process becomes a slave. In this mode, the slave:

- Binds to a random UDP port.
- Sends a single number to the master.
- Exits after successfully sending the message.

---

### 🧱 Code Structure

#### 📄 Main Class: `DAS.java`

Handles all logic for both master and slave modes.

#### 🧩 Core Methods:

- `main(String[] args)` – Parses input, detects role, and initiates corresponding mode.
- `runMaster()` – Handles incoming messages and system behavior in master mode.
- `runSlave()` – Sends number to master and terminates.
- `broadcast(String message)` – Used by master to send messages to all clients on the local network.

---

### 🧗 Challenges

One of the key challenges was ensuring **reliable communication** between master and slave over UDP, especially when slaves exited immediately after sending.  
Handling timing, port conflicts, and proper broadcast behavior required careful testing and debugging.

---

🛠️ *Developed for the SKJ course to explore network programming and distributed systems using Java.*
