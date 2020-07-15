//package blackjackself;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;



public class ServerA
{
    public static void main(String[] args) throws Exception
    {
        try (var listener = new ServerSocket(7777))
        {
            System.out.println("Serversock is working");
            var pool = Executors.newFixedThreadPool(20);
            while (true)
            {
                pool.execute(new AccountManage(listener.accept()));
            }
        }
    }

    private static class AccountManage implements Runnable
    {
        private Socket socket;

        AccountManage(Socket socket)
        {
            this.socket = socket;
        }
    

        @Override
        public void run()
        {
            System.out.println("Connected: " + socket);
            try 
            {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                
                

                Message clientMessage = (Message)in.readObject();
                System.out.println(clientMessage.getInput());

                Message sendBack = new Message(clientMessage.getType(), clientMessage.getInput());
                out.writeObject(sendBack);

                //out.writeObject(clientMessage);
                
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + socket);
            }
            finally 
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {

                }
                System.out.println("Closed: " + socket);
                
            }
        }
    }
}
