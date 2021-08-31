package Ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IpServer {

private static InetAddress address;


    public static void main(String[] args) {

        System.out.println("Input a URL you wanna find the ip of");
        Scanner sc = new Scanner(System.in);

        try{
            address = InetAddress.getByName(sc.next());
            System.out.println("IP address is: "
                    + address.toString());
            address = InetAddress.getLocalHost();
            System.out.println("Local address is: "
                    + address.toString());

        }catch (UnknownHostException ex ){
            ex.printStackTrace();
        }
    }
}
