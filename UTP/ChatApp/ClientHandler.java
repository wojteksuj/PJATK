import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    public static List<ClientHandler> clientHandlers = new ArrayList<>();
    public static List<String> clientsUsernames = new ArrayList<>();

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    public String username;
    List<String> bannedWords;

    public ClientHandler(Socket socket, List<String> bannedWords) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bannedWords = bannedWords;

        requestUsername();

        synchronized (clientHandlers) {
            clientHandlers.add(this);
            clientsUsernames.add(this.username);
        }

        broadcast("SERVER: " + username + " has joined the chat");
        bufferedWriter.write("SERVER: Welcome " + username + "! List of all connected users: " + clientsUsernames.toString() + "\n" +
                "*****************INSTRUCTION*****************\n" +
                "1. By default message is sent to all users\n" +
                "2. Sending message to specific person: @nickname: 'msg'\n" +
                "3. Sending message to multiple people: @nickname1,nickname2,etc: 'msg'\n" +
                "4. Sending message to everyone except some people: !@nickname1,nickname2,etc: 'msg'\n" +
                "5. Query the list of banned phrases: /listbanned\n" +
                "*********************************************");
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    private synchronized void requestUsername() throws IOException {
        while (true) {
            this.username = bufferedReader.readLine();

            if (!isUsernameTaken(this.username)) {
                break;
            } else {
                bufferedWriter.write("SERVER: Username is already taken. Please try again.");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
    }

    private synchronized boolean isUsernameTaken(String username) {
        return clientsUsernames.contains(username);
    }

    @Override
    public void run() {
        String msgFromClient;
        try {
            while ((msgFromClient = bufferedReader.readLine()) != null) {
                broadcast(msgFromClient);
            }
        } catch (IOException e) {
            removeClientHandler();
            closeResources();
        } finally {
            removeClientHandler();
            closeResources();
        }
    }

    public synchronized void broadcast(String msgToSend) {
        if (containsBannedWords(msgToSend)) {
            for (ClientHandler client : clientHandlers) {
                if (client.username.equals(this.username)) {
                    try {
                        client.bufferedWriter.write("SERVER: Your message contains a prohibited word. Try again!");
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException e) {
                        System.out.println("Error while sending a message");
                    }
                }
            }
        } else if (msgToSend.equals("/listbanned")) {
            for (ClientHandler client : clientHandlers) {
                if (client.username.equals(this.username)) {
                    try {
                        client.bufferedWriter.write("SERVER: List of banned phrases: " + bannedWords.toString());
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException e) {
                        System.out.println("Error while sending a message");
                    }
                }
            }

        } else if (msgToSend.startsWith("SERVER:")) {
            for (ClientHandler client : clientHandlers) {
                try {
                    client.bufferedWriter.write(msgToSend);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                } catch (IOException e) {
                    System.out.println("Error while sending a message");
                }
            }
        } else if (msgToSend.startsWith("@")) {
            int index = msgToSend.indexOf(":");
            String temp = msgToSend.substring(1, index);
            String[] usernameTargets = temp.split(",");

            String privateMsg = msgToSend.substring(index + 1).trim();

            for (ClientHandler client : clientHandlers) {
                if (containsUsername(usernameTargets, client.username)) {
                    try {
                        client.bufferedWriter.write(this.username + ": " + privateMsg);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException e) {
                        System.out.println("Error while sending a message");
                    }
                }
            }
        } else if (msgToSend.startsWith("!@")) {
            int index = msgToSend.indexOf(":");
            String temp = msgToSend.substring(2, index);
            String[] usernameTargets = temp.split(",");

            String privateMsg = msgToSend.substring(index + 1).trim();

            for (ClientHandler client : clientHandlers) {
                if (!containsUsername(usernameTargets, client.username) && !(this.username.equals(client.username))) {
                    try {
                        client.bufferedWriter.write(this.username + ": " + privateMsg);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException e) {
                        System.out.println("Error while sending a message");
                    }
                }
            }
        } else {
            for (ClientHandler client : clientHandlers) {
                if (!client.username.equals(this.username)) {
                    try {
                        client.bufferedWriter.write(this.username + ": " + msgToSend);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException e) {
                        System.out.println("Error while sending a message");
                    }
                }
            }

        }
    }

    public boolean containsUsername(String[] arr, String username) {
        for (String s : arr) if (s.equals(username)) return true;
        return false;
    }

    public boolean containsBannedWords(String message) {
        for (String word : bannedWords) {
            if (message.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void removeClientHandler() {
        broadcast("SERVER: " + this.username + " has disconnected");
        synchronized (clientHandlers) {
            clientHandlers.remove(this);
            clientsUsernames.remove(this.username);
        }
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
}
