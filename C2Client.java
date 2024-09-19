import java.io.*;
import java.net.*;
public class C2Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 80;
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to C2 Server");
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input  = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // send commands to c2
            writer.println("echo Hello from client");
            String response = reader.readLine();
            System.out.println("Server: " + response);
            socket.close();
        }   catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        }   catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
