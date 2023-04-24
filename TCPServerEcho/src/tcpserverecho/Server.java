/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserverecho;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Bernardi Nicola
 */
public class Server {
    private int serverPort;
    private ServerSocket serverSocket;
    private DataInputStream networkIn;
    private DataOutputStream networkOut;
    
    public Server(int port){
        try {
            serverPort=port;
            // creazione del socket di attesa
            serverSocket = new ServerSocket(port);                  
        } catch (IOException ex) {
        }
    }    
  
    public void eseguiServer() {
        Socket socketAttivo;
        while(true) {    
            try {
                // creazione del socket attivo
                socketAttivo=serverSocket.accept();
                //ciclo di ricezione dal client e invio di risposta
                while(true) {
                    try {
                        networkIn = new DataInputStream(socketAttivo.getInputStream());
                        networkOut = new DataOutputStream(socketAttivo.getOutputStream());
                        String s ;
                        s = networkIn.readLine();
                        
                        networkOut.write((s+"\n").getBytes());
                        networkOut.flush();
                        s.replaceAll("\n", "");
                        if (s.equals("quit")){
                            networkIn.close();
                            networkOut.close();
                            socketAttivo.close();
                            break;
                        }
                        System.out.println("Echoing: "+ s);
                    } catch (IOException ex) {
                    }
                }
                
            } catch (IOException ex) {
            }
        }
    }  
}