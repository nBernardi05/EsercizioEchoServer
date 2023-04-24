package tcpclientecho;
// EchoClient

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TCPClientEcho {
    public static void  main(String[] args) {
        try {
            Client client=new Client(InetAddress.getLocalHost(),50000);
            client.eseguiClient();
            client.chiudiClient();
        } catch (UnknownHostException ex) {
        }
    }
} 

