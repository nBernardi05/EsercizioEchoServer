package tcpserverecho;
// EchoServer
public class TCPServerEcho {
    public static void main(String[] args) {
        Server server=new Server(50000);
        server.eseguiServer();
    } 
}



