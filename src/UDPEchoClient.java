import java.io.*;
import java.net.*;
import java.util.*;
public class UDPEchoClient {
    private static InetAddress host;

    private static final int PORT = 1234;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost(); //il récupère l'adresse ip du serveur
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }
    private static void accessServer()
    {
        try
        {
//Etape 1...
            datagramSocket = new DatagramSocket();
//Configurer le flux pour l'entrée du clavier...
            Scanner userEntry = new Scanner(System.in);
            String message="", response="";
            do
            {
                System.out.print("Enter message: ");
                message = userEntry.nextLine();
                if (!message.equals("CLOSE"))
                {
                    outPacket = new DatagramPacket(message.getBytes(), message.length(), host, PORT); //le host est l'adresse du serveur et le port est du serveur
//Etape 2.
//Etape 3...
                    datagramSocket.send(outPacket);
                    buffer = new byte[256]; //Etape 4.
                    inPacket = new DatagramPacket(buffer, buffer.length);//Etape 5.
//Etape 6...
                    datagramSocket.receive(inPacket);
                    response = new String(inPacket.getData(), 0, inPacket.getLength());
//Etape 7.
                    System.out.println("\nSERVER> "+response);
                }
            }while (!message.equals("CLOSE"));
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            System.out.println("\n* Closing connection... *");
            datagramSocket.close();//Etape 8.
        }
    }
}