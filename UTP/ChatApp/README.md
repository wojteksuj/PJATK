## 💬 Java Socket Chat Application

**Java Socket Chat Application** is a real-time, multi-user chat system built using Java sockets. It features a configurable server, banned words filtering, flexible message targeting, and a command-based console interface for both clients and the server.

---

### 📝 Description

This application enables multiple users to connect to a centralized server and chat in real-time. The server enforces banned word policies, supports different messaging modes (broadcast, private, group, exclusion), and provides user session management. All settings are configurable via an external file.

---

### ✨ Features

- 👥 **Multi-client support:** Multiple users can connect and communicate simultaneously.
- ⚙️ **Configurable server:** Uses `serverConfiguration.txt` to load:
  - Server name
  - Port
  - Banned words list
- 🚫 **Banned words filtering:** Messages with banned content are blocked, and users are notified.
- 🎯 **Flexible messaging modes:**
  - 📨 **Broadcast:** Messages sent to all users (default).
  - 🔒 **Private:** Use `@username: message` to direct a message to one user.
  - 👥 **Group:** Use `@user1,user2: message` for a message to specific users.
  - 🚫 **Exclusion:** Use `!@user1,user2: message` to message everyone except selected users.
- 🧾 **Commands:**
  - `/listbanned` — Displays banned phrases.
- 🔐 **User management:**
  - Unique usernames enforced
  - Notifications when users join/leave
- 🧼 **Clean shutdown:** Handles closing sockets and threads gracefully.

---

### 🗂️ Project Structure

- `Server.java` – Loads configuration, initializes the server, manages connections.
- `ClientHandler.java` – Manages per-client messaging, filtering, and routing.
- `Client.java` – Connects to the server, handles user input/output.
- `serverConfiguration.txt` – Contains server name, port, and banned words list.

---

### 🚀 Getting Started

1. **Edit configuration:**
   - Open `serverConfiguration.txt` and set your server name, port, and banned phrases.

2. **Start the server:**
   - Run `Server.java`. The server will wait for client connections.

3. **Start a client:**
   - Run `Client.java`, provide the server IP, port, and a username.

4. **Begin chatting:**
   - Type to broadcast
   - Use:
     - `@username: message` for private messages
     - `@user1,user2: message` for group messages
     - `!@user1,user2: message` for exclusion-based messaging
   - `/listbanned` to view banned words

---

💻 *Created as a Java networking project for the UTP course, showcasing real-time communication with message parsing and server control features.*
