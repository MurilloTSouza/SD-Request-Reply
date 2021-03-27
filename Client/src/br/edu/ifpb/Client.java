package br.edu.ifpb;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static String HOST;
    private static final int PORT = 1234;

    public static void main(String[] args) throws Exception{

        // initializing HOST IP address from localhost,
        // considering that server and client are in the same host
        HOST = InetAddress.getLocalHost().getHostName();

        System.out.println("Client started.");
        Scanner scanner = new Scanner(System.in);

        while(true){
            // Receive message from user
            System.out.println("\nPlease type your message and press ENTER. (type 'exit' to close Client)");
            String message = scanner.nextLine();
            if(message.equals("exit")) break; // close if user type 'exit'

            // send user message to server
            String reply = doOperation(message);

            // print response from server
            System.out.println("Response from server:");
            System.out.printf("\t- %s\n", reply);
        }

        System.out.println("Finishing Client.");

    }

    /**
     * Synchronous blocking method that send request to server
     * and wait until reply(response) is retrieved
     *
     * @return message from response returned from server
     */
    private static String doOperation(String message) throws Exception{

        // connect to server
        Socket socket = new Socket(HOST, PORT);

        // writing message to request and sending it to Server
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        System.out.print("Sending request to Server...");
        output.writeObject(message);
        System.out.println("Message send");

        // retrieving message from response
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        String reply = (String) input.readObject();

        // closing connection
        output.close();
        input.close();
        socket.close();

        // returning response message
        return reply;
    }
}
