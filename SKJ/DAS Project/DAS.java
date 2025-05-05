import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class DAS {

    public static void main(String[] args) {

        int port;
        int number;

        port = Integer.parseInt(args[0]);
        number = Integer.parseInt(args[1]);

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Master mode activated on port " + port);
            runMaster(socket, number);
        } catch (SocketException e) {
            System.out.println("Slave mode activated.");
            runSlave(port, number);
        }
    }

    private static void runMaster(DatagramSocket socket, int initialNumber) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(initialNumber);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try {
            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                int receivedNumber = Integer.parseInt(message);
                System.out.println("Received: " + receivedNumber);

                if (receivedNumber == 0) {
                    int sum = 0;
                    int count = 0;
                    for (int num : numbers) {
                        if (num != 0) {
                            sum += num;
                            count++;
                        }
                    }
                    int average = (count > 0) ? sum / count : 0;
                    System.out.println("Average: " + average);
                    broadcast(socket, packet.getAddress(), packet.getPort(), average);
                } else if (receivedNumber == -1) {
                    System.out.println("Received -1 + \nClosing socket...");
                    broadcast(socket, packet.getAddress(), packet.getPort(), -1);
                    socket.close();
                    break;
                } else {
                    numbers.add(receivedNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runSlave(int masterPort, int number) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress localHost = InetAddress.getByName("localhost");
            byte[] data = String.valueOf(number).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, localHost, masterPort);
            socket.send(packet);
            System.out.println("Sent " + number + " to master on port " + masterPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(DatagramSocket socket, InetAddress address, int port, int value) {
        try {
            String message = String.valueOf(value);
            byte[] data = message.getBytes();
            DatagramPacket broadcastPacket = new DatagramPacket(data, data.length, address, port);
            socket.send(broadcastPacket);
            System.out.println("Broadcasted: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
