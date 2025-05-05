# Java Socket Chat Application

**Java Socket Chat Application** is a real-time, multi-user chat system built with Java sockets. The project features a configurable server with banned words filtering, flexible message targeting, and a command-based user interface. Both the server and clients run from the console.

## Description

This application allows multiple clients to connect to a central server and exchange messages in real time. The server manages user sessions, enforces banned words filtering, and supports various message delivery modes: broadcast, private, group, and exclusion-based messaging. Configuration is handled via an external file, making it easy to set the server name, port, and banned words.

## Features

- **Multi-client support:** Multiple users can connect and chat simultaneously.
- **Configurable server:** Server name, port, and banned words are loaded from `serverConfiguration.txt`.
- **Banned words filtering:** Messages containing banned phrases are blocked and users are notified.
- **Flexible messaging:**
  - **Broadcast:** Messages sent to all users by default.
  - **Private:** Use `@username: message` to send to a specific user.
  - **Group:** Use `@user1,user2: message` to send to multiple users.
  - **Exclusion:** Use `!@user1,user2: message` to send to everyone except listed users.
- **User commands:**
  - `/listbanned` - View all banned phrases.
- **User management:** Unique usernames enforced; users are notified on join/leave.
- **Instructions:** Clear usage instructions sent to users on connection.
- **Clean resource handling:** Graceful shutdown and cleanup of sockets and threads.

## Project Structure

- `Server.java` - Loads configuration, starts the server, manages connections.
- `ClientHandler.java` - Handles individual client communication, message parsing, and enforcement of rules.
- `Client.java` - Connects to the server, handles user input/output, and manages messaging.
- `serverConfiguration.txt` - External configuration for server name, port, and banned words.

## Getting Started

1. **Configure the server:**  
   Edit `serverConfiguration.txt` to set the server name, port, and banned words.

2. **Start the server:**  
   Run `Server.java`. The server will listen for incoming client connections.

3. **Start a client:**  
   Run `Client.java` and provide the server's IP, port, and your desired username when prompted.

4. **Chat:**  
   - Type messages to broadcast.
   - Use `@username: message` for private, `@user1,user2: message` for group, and `!@user1,user2: message` for exclusion-based messaging.
   - Type `/listbanned` to see banned words.

---

*Developed as a Java networking project for real-time, command-driven chat communication.*
