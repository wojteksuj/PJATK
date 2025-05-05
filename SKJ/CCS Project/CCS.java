import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CCS {
    private static int udpPort;
    private static int tcpPort;
    private static int clientCount = 0;
    private static int requestCount = 0;
    private static Map<String, Integer> operationCount = new ConcurrentHashMap<>();
    private static int incorrectOperations = 0;
    private static int sumOfValues = 0;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar CCS.jar <port>");
            return;
        }

        try {
            udpPort = Integer.parseInt(args[0]);
            tcpPort = udpPort;
            new Thread(CCS::startUDPServer).start();
            new Thread(CCS::startTCPServer).start();
            new Thread(CCS::reportStatistics).start();
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number.");
        }
    }

    private static void startUDPServer() {
        try (DatagramSocket udpSocket = new DatagramSocket(udpPort)) {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.startsWith("CCS DISCOVER")) {
                    byte[] response = "CCS FOUND".getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
                    udpSocket.send(responsePacket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startTCPServer() {
        try (ServerSocket serverSocket = new ServerSocket(tcpPort)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
                synchronized (CCS.class) {
                    clientCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                String[] parts = request.split(" ");
                if (parts.length != 3) {
                    out.println("ERROR");
                    synchronized (CCS.class) {
                        incorrectOperations++;
                    }
                    continue;
                }
                String operation = parts[0];
                int arg1, arg2;
                try {
                    arg1 = Integer.parseInt(parts[1]);
                    arg2 = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    out.println("ERROR");
                    synchronized (CCS.class) {
                        incorrectOperations++;
                    }
                    continue;
                }

                int result;
                switch (operation) {
                    case "ADD": result = arg1 + arg2; break;
                    case "SUB": result = arg1 - arg2; break;
                    case "MUL": result = arg1 * arg2; break;
                    case "DIV":
                        if (arg2 == 0) {
                            out.println("ERROR");
                            synchronized (CCS.class) {
                                incorrectOperations++;
                            }
                            continue;
                        }
                        result = arg1 / arg2;
                        break;
                    default:
                        out.println("ERROR");
                        synchronized (CCS.class) {
                            incorrectOperations++;
                        }
                        continue;
                }

                out.println(result);
                synchronized (CCS.class) {
                    requestCount++;
                    operationCount.merge(operation, 1, Integer::sum);
                    sumOfValues += result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void reportStatistics() {
        while (true) {
            try {
                Thread.sleep(10000);
                synchronized (CCS.class) {
                    System.out.println("Statistics since start:");
                    System.out.println("Clients connected: " + clientCount);
                    System.out.println("Total requests processed: " + requestCount);
                    System.out.println("Operations breakdown: " + operationCount);
                    System.out.println("Incorrect operations: " + incorrectOperations);
                    System.out.println("Sum of all results: " + sumOfValues);
                    System.out.println('\n');
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
