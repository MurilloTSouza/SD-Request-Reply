package br.edu.ifpb;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // PORT which the server will listen
    private static final int PORT = 1234;
    private static ServerSocket server;

    public static void main(String[] args) throws Exception{

        // initiating server in this port
        server = new ServerSocket(PORT);
        System.out.printf("Server is now running on port %d.\n", PORT);

        // keep server listen
        while(true){

            // block and wait for request
            Socket socket = getRequest();

            // continuing after request received
            System.out.println("Request received.");

            // retrieve message from request
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String message = (String) input.readObject();
            System.out.println("Client says:");
            System.out.printf("\t- %s\n", message);

            // send reply (response) to client
            String response = "Thank you. '"+message+"' received.";
            sendReply(socket, response);
            input.close(); // closing input
        }

        // use it to close server
        // server.close();
    }

    /**
     * Synchronous blocking method that keep listening for client request.
     *
     * @return Socket object after the connection
     * @throws Exception
     */
    private static Socket getRequest() throws Exception{

        // block and wait for connection
        System.out.print("\nWaiting for the client request...");
        return server.accept();
    }

    /**
     * Send response do client containing a String message
     *
     * @param socket socket which the connection is established
     * @param message message to send to client in response
     * @throws Exception
     */
    private static void sendReply(Socket socket, String message) throws Exception{
        // write message to reply(response)
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(message);

        output.close(); // closing output
        socket.close(); // close connection
    }

}
