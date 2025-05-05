import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        String serverIP = "localhost";  // Change this to the server IP address if the server is not running locally
        int serverPort = 123;  // The TCP port number the server is listening on

        try {
            // Connect to the server at the specified IP and port
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to the server at " + serverIP + ":" + serverPort);

            // Input and output streams to communicate with the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // For taking user input from the console
            Scanner scanner = new Scanner(System.in);

            // Client interaction loop
            while (true) {
                // Ask the user for a calculation request (e.g., ADD 10 5)
                System.out.println("Enter a request (e.g., ADD 10 5), or type 'exit' to quit:");
                String request = scanner.nextLine();

                // If the user types 'exit', close the connection and break out of the loop
                if (request.equalsIgnoreCase("exit")) {
                    break;
                }

                // Send the request to the server
                out.println(request);

                // Receive and print the response from the server
                String response = in.readLine();
                System.out.println("Response from server: " + response);
            }

            // Close resources
            in.close();
            out.close();
            socket.close();
            scanner.close();
            System.out.println("Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
