//Serveur qui renvoie les messages du client.
//En fin de dialogue, envoie un message indiquant le nombre de
//messages reçus. Utilise des datagrammes.
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPEchoServer {
    private static final int PORT = 1234;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    public static void main(String[] args) {
        System.out.println("Opening port...");

        try
        {
            datagramSocket = new DatagramSocket(PORT);
        }
//Etape 1.
        catch(SocketException sockEx)
        {
            System.out.println("Unable to open port!");
            System.exit(1);
        }
        handleClient();
    }
    private static void handleClient()
    {
        try
        {
            String messageIn,messageOut; int numMessages = 0;
            InetAddress clientAddress = null;
            int clientPort;
            do
            {

                buffer = new byte[256]; //Etape 2.
                inPacket = new DatagramPacket( buffer, buffer.length);

//Etape 3.
                datagramSocket.receive(inPacket);
//Etape 4.
                clientAddress = inPacket.getAddress();
//Etape 5.
                clientPort = inPacket.getPort();
//Etape 5.
                messageIn = new String(inPacket.getData(), 0,inPacket.getLength());

//Etape 6.
                System.out.println("\nCLIENT> "+messageIn);
                System.out.print("Enter response: ");
                //numMessages++;
                //messageOut = "Message " + numMessages + ": " + messageIn;
                Scanner userEntry = new Scanner(System.in);
                messageOut = userEntry.nextLine();
                outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(),clientAddress, clientPort); //Etape 7.
                datagramSocket.send(outPacket); //Etape 8.
            }while (true);
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally //Si l'exception est levé, fermer la connection.
        {
            System.out.println("\n* Closing connection... *");

            datagramSocket.close(); //Etape 9.

        }
    }
}