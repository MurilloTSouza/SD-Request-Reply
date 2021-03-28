# SD-Request-Reply
A simple implementation of a request-reply(RR) protocol using Java and Socket.

## How to run
Use the **.sh** script to start the server and/or client. In the root folder, run:
- `sh ./server.sh` to start the Server.
- `sh ./client.sh` to start the Client.

You can also run the Java *.class* directly from the *classes/* folder

**Warning**: This project only work if server and client are in the same host.

## How it works
The **Server** class will call the `getRequest()` method and start to listening the port `1234`.
This method will call `Socket.accept()` and wait for a request from Client.

`Socket.accpet()` works synchronously and therefore will block the process (thread) until a request is received.

Meanwhile, the **Client** application will ask the user for a message and after that, 
that message is passed to `doOperation()` method.

In the `doOpeartion()` method a new Socket is created, connecting to the **Server**
and sending a request containg the user message written in it.

The **Server** will receive that request and retrieve the message, 
returning a response using the `sendReply()` method, sending a message to the **Client** that the request was received.

When the **Client** receives the response, the confirmation message from the **Server** is displayed.
