/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclientecho;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Bernardi Nicola
 */
public class Client {
    private InetAddress serverAddr;
    private int serverPort;
    private Socket socket;
    private DataInputStream networkIn;
    private DataOutputStream networkOut;
    
    public Client(InetAddress addr, int port){
        try {
            serverAddr=addr;
            serverPort=port;
            // creazione del socket
            socket = new Socket(addr, port);
            // creazione stream di input da socket   
            
        } catch (IOException ex) {
        }
    }
    
    public void eseguiClient() throws IOException {
        // creazione stream di input da tastiera
        Scanner stIn=new Scanner(System.in);
        String input;
        // ciclo di lettura da tastiera, invio al server e stampa risposta
        while(true){
            try {
                networkIn = new DataInputStream(socket.getInputStream());
                networkOut=new DataOutputStream(socket.getOutputStream());
                System.out.println("Inserisci una frase (quit per uscire)");
                input=stIn.nextLine();
                networkOut.write((input+"\n").getBytes());
                networkOut.flush();
                
                String s =networkIn.readLine();
                s.replaceAll("\n", "");
                
                if (s.equals("quit")){
                    break;
                }
                System.out.println("Echo: "+ s);
            } catch (IOException ex) {
            }
        }
        
    }
    
    public void chiudiClient(){
        try {
            networkIn.close();
            networkOut.close();
            socket.close();
        } catch (IOException ex) {
        }
    }
        
    
}
