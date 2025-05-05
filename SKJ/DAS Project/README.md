# Project: Distributed Averaging System (DAS)

## Introduction

This project implements a **Distributed Averaging System (DAS)** in Java, where processes can run in two modes: **master** and **slave**. These modes are automatically determined based on the availability of a specified UDP port.

The program demonstrates message passing and computation across distributed systems using **UDP sockets**.

---

## ✅ Implemented Features

- **Master Mode**
- **Slave Mode**

### 🔹 Master Mode

If the program successfully opens the specified UDP port, it enters **master mode**. In this mode:

- It receives messages from slaves using UDP.
- The first number stored is the one provided when the master program is launched.
- Subsequent numbers are received from slave processes.
- Behavior based on received number:
  - If `0`: Calculates the average of all stored numbers and broadcasts the result to all devices on the same port.
  - If `-1`: Broadcasts `-1` to all devices, closes the socket, and terminates.
  - Any other number: Prints it to the console and stores it for future calculations.

### 🔹 Slave Mode

If the program cannot open the specified port (another instance is running as master), it enters **slave mode**. In this mode:

- It creates a UDP socket with a random port.
- Sends the specified number to the master running on the given port.
- Terminates immediately after sending the message.

---

## 🧩 Implemented Classes

- **`DAS`** – The main class containing logic for both master and slave modes.

---

## 🧪 Implemented Methods

- **`main`**  
  Parses command-line arguments (port and number), determines which mode to run, and calls either `runMaster` or `runSlave`.

- **`runMaster`**  
  Implements the full logic for master mode.

- **`runSlave`**  
  Implements the full logic for slave mode.

- **`broadcast`**  
  Used in master mode to broadcast a message to all clients on the local network.

---

## ⚠️ Difficulties

The main difficulty was ensuring correct communication between the master and slave. Issues included:

- Ensuring the slave correctly sent its number to the master.
- Handling the master's behavior when receiving various messages.
- Implementing the master mode logic correctly.
- Detecting and switching to slave mode when the port was already in use.

---
