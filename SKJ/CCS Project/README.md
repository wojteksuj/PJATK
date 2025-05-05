## 🖥️ Project: Centralized Computing System (CCS)

### 🧾 Overview

The **Centralized Computing System (CCS)** is a Java-based server application demonstrating concepts of service discovery, client-server architecture, multithreading, and statistics tracking. It utilizes both **UDP** for discovery and **TCP** for communication with clients.

---

### ✅ Implemented Features

- 🔍 **Service Discovery (UDP):**  
  Listens on a given port for messages starting with `"CCS DISCOVER"` and responds with `"CCS FOUND"` to help clients locate the server.

- 🔗 **Client Communication via TCP:**  
  Accepts incoming client connections over TCP. Each client is handled in its own thread to support concurrency.

- 🧮 **Request Handling:**  
  Processes requests of the form:  
Executes the requested arithmetic operation and responds with the result or an error message.

- 📊 **Statistics Reporting:**  
Tracks:
- Number of active clients
- Total number of requests
- Count of operations performed
- Number of invalid operations
- Sum of all computed results  
📅 Reports are printed to the console every **10 seconds**.

---

### ❌ Not Implemented

- **Graceful Shutdown:**  
The system does not yet support graceful shutdown, such as cleanly closing sockets or saving state before exiting.

---

### 🧱 Code Structure

- **`CCS.java`** — Main class containing:
- UDP server for discovery
- TCP server for client connections
- Logic for handling operations and tracking statistics

---

### 🧗 Challenges Faced

- 🧵 Managing concurrent client connections while avoiding race conditions during shared state updates.
- 🧮 Correctly computing and formatting live statistics under concurrent load.
- ⚠️ Ensuring error handling for malformed client requests without crashing the server.

---

🛠️ *This project was developed for the SKJ (Computer Networks and Network Programming in Java) course.*
