import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server {
    private String serverName;
    private int port;
    private ServerSocket serverSocket;
    private List<String> bannedWords = new ArrayList<>();
    private ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public Server(String configPath) throws IOException {
        loadConfig(configPath);
    }

    public void startServer() throws IOException {
        System.out.println("The " + serverName + " has started!");
        serverSocket = new ServerSocket(port);

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New user has joined!");
                ClientHandler clientHandler = new ClientHandler(socket, bannedWords);
                executor.submit(clientHandler);
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    public synchronized void closeServer() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
            System.out.println("Server has been closed.");
        }
        if (executor != null) executor.close();
    }

    public void loadConfig(String configPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(configPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keys = line.split("=");
                switch (keys[0]) {
                    case "serverName":
                        serverName = keys[1];
                        break;
                    case "port":
                        port = Integer.parseInt(keys[1]);
                        break;
                    case "bannedWords":
                        String[] words = keys[1].split(",");
                        Collections.addAll(bannedWords, words);
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong configuration key: " + keys[0]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server("serverConfiguration.txt");
        server.startServer();
    }
}
