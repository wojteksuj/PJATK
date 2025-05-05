import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDiscoveryClient {

    public static void main(String[] args) {
        try {
            // Set the broadcast IP address and port
            String broadcastAddress = "255.255.255.255";  // You can replace this with your network's broadcast address
            int port = 123;  // The port your server is listening on

            // Create a UDP socket
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);  // Enable broadcast

            // Prepare the discovery message
            String message = "CCS DISCOVER";
            byte[] buffer = message.getBytes();

            // Send the UDP packet to the server via broadcast
            InetAddress address = InetAddress.getByName(broadcastAddress);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            System.out.println("Discovery message sent!");

            // Prepare to receive a response from the server
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

            // Wait for the response (blocking)
            socket.receive(responsePacket);

            // Convert the response to a string and print it
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Response from server: " + response);

            // Close the socket
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
