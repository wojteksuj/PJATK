# Project: Centralized Computing System (CCS)

## Implemented features

- **Service Discovery:** A UDP server was implemented to listen on the provided port for discovery messages that start with `"CCS DISCOVER"`. Upon receiving such a message, the server replies with `"CCS FOUND"`.
- **Client Communication via TCP:** A TCP server listens on the same port for incoming client connections. Each client connection is handled in a separate thread to allow concurrent client interactions.
- **Request Handling:** The server processes requests in the format `<OPER> <ARG1> <ARG2>`, performs the specified arithmetic operation, and sends the result or an error message back to the client.
- **Statistics Reporting:** The server tracks statistics such as the number of clients connected, total requests processed, operation counts, incorrect operations, and the sum of all results. These statistics are printed every 10 seconds.

## Non-implemented features

- **Graceful Shutdown:** There is no explicit handling for graceful server shutdown, which could involve closing open sockets and saving state.

## Implemented classes

- **CCS:** This is the main class of the program containing all the logic to handle UDP and TCP servers and report statistics.

## Difficulties

The main difficulty I encountered was managing concurrent client connections while ensuring thread safety and consistent data handling, especially when updating shared statistics and handling multiple requests simultaneously. Another challenge was to correctly calculate statistics and report them accurately.
