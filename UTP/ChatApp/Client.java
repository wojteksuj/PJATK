import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();


    public Client(Socket socket, String username) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.username = username;
    }

    public synchronized void sendMsg() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                if (message.trim().isEmpty()) {
                    continue;
                }
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("Error while sending a message");
        } finally {
            closeResources();
        }
    }

    public synchronized void listenToMsg() {
        executor.submit(() -> {
            String msgFromServer;
            try {
                while ((msgFromServer = bufferedReader.readLine()) != null) {
                    System.out.println(msgFromServer);
                }
            } catch (IOException e) {
                System.out.println("Error while listening to the messages");
            }
        });
    }

    private synchronized void closeResources() {
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error while closing resources");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String host;
        int port;
        String username;

        System.out.print("Enter server's IP: ");
        host = scanner.nextLine();
        System.out.print("Enter server's port: ");
        port = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter your username: ");
        username = scanner.nextLine();

        try (Socket socket = new Socket(host, port)) {
            Client client = new Client(socket, username);

            client.listenToMsg();
            client.sendMsg();
        } catch (IOException e) {
            System.out.println("Error while connecting to the server");
        }
    }
}
