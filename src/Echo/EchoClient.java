package Echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            DataInputStream fromServer = new DataInputStream(socket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());


            Scanner sc = new Scanner(System.in);

            while (true) {
                toServer.writeUTF(sc.nextLine());
                toServer.flush();
                System.out.println(fromServer.readUTF());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }
}