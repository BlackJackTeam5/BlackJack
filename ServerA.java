import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;


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
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine())
                {
                    out.println("Test test test!");
                }
                
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
