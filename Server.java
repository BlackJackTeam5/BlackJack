import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create a ServerSock on localhost:7777
        ServerSocket ss = new ServerSocket(7777);
        Map<String,String> playerInfo = new HashMap<String,String>();
        playerInfo.put("Dodo", "Chip");
        System.out.println("ServerSocket awaiting connections...");
        while (true)
        {

            try (Socket socket = ss.accept())
            {
                // .accept blocks until an inbound connection on this port is attempted
                //Socket socket = ss.accept();
                System.out.println("Connection from " + socket + "!");

                

                // get the input stream from the connected socket
                InputStream inputStream = socket.getInputStream();

                // create a ObjectInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

              
                // read the list of messages from the socket
                List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();

                try
                {
                    String password = playerInfo.get(listOfMessages.get(0).getText());
                    if (listOfMessages.get(1).getText().equals(password)){
                        System.out.printf("You are now logged in.\n");
                    }
                    else
                    {
                        System.out.printf("Wrong password.\n");
                        System.out.printf(listOfMessages.get(1).getText());
                    }
                    
                }
                catch(Exception e)
                {
                    System.out.printf("Wrong username.\n");
                }


                // System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);

                // // for every message, call printMessage(message);
                // System.out.println("All messages:");
                // listOfMessages.forEach(msg -> printMessage(msg));

                System.out.println("Closing sockets.");
            }
        }
        // ss.close();
        // socket.close();
    }
    
    private static void printMessage(Message msg){
        System.out.println("Message #: " + msg.getMessageCount());
        System.out.println("Type: " + msg.getType());
        System.out.println("Text: " + msg.getText());
    }

}
