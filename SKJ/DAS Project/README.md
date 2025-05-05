# Project: Distributed Averaging System (DAS)

## Introduction

This project implements a Distributed Averaging System (DAS) in Java, where processes can run in two modes: master and slave. These modes are automatically determined based on the availability of a specified UDP port. The program demonstrates message passing and computation across distributed systems using UDP sockets.

## Implemented features

- Master mode
- Slave mode

### Master mode

The program enters master mode if it successfully opens the specified UDP port. In this mode, it receives messages from slaves using UDP and stores all received numbers. The first number stored is the number provided when the master program is launched; subsequent numbers are those sent by slaves.

If the master receives the number `0`, it calculates the average of all stored numbers and broadcasts the result to all devices connected on the same port.  
If it receives the number `-1`, it broadcasts `-1` to all connected devices, closes the socket, and terminates.  
If the master receives any other number, it prints it to the console and stores it in memory for future calculations.

### Slave mode

The program enters slave mode if it cannot open the specified port, which happens if another instance is already running as the master. In slave mode, the program:

- Creates a UDP socket with a random port
- Sends the specified number to the master process running on the given port
- Terminates immediately after sending the message

## Implemented classes

- DAS – this is the main class of the program containing the logic for both master and slave modes

## Implemented methods

- `main` – parses command-line arguments (port and number), determines which mode to run, and calls either `runMaster` or `runSlave`
- `runMaster` – implements the full logic for master mode
- `runSlave` – implements the full logic for slave mode
- `broadcast` – used by master mode to broadcast a message to all clients connected on the local network

## Difficulties

The main difficulty I encountered was managing correct communication between the master and slave. I faced issues with the slave sending a number to the master and ensuring t
