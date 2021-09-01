package Echo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {


    public static void main(String[] args) {

            try{
                ServerSocket serverSocket = new ServerSocket(8080);


                Socket socket = serverSocket.accept();
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());
                while(true){
                    String clientMessage = inputFromClient.readUTF();
                    outputToClient.writeUTF("The message recived is " + clientMessage);

                }

            }catch (IOException exception){
                exception.printStackTrace();
            }

    }
    }

