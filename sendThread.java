/*
 * EchoClient.java
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.*;

public class sendThread extends Thread {
    
            
        DatagramSocket datagram;    	// socket de datagrama
        InetAddress address;        	// endereco maquina destino     
        int remotePort;                 //nro da porta da maquina destino

        public sendThread(DatagramSocket datagram_, InetAddress address_, int remotePort_){
                this.datagram = datagram_;
                this.address = address_;
                this.remotePort = remotePort_;
        }

        public void run(){
                //variaveis
                DatagramPacket requestPacket;               
                Scanner stdIn;       		// leitura de dados
                byte[] buffer;              // buffer de dados
		        String message;				// mensagem enviada/recebida
                message = null;
                //instancia o scanner                
                stdIn = new Scanner(System.in);
           
            do {      
                try{
                    // le mensagem
                    System.out.print("Enviado -> ");
                    message = stdIn.nextLine();
                    System.out.print("\n");
                    // monta o pacote
                    buffer = message.getBytes();
                    requestPacket = new DatagramPacket(buffer, buffer.length, address, remotePort);
                
                    // envia pacote pelo socket
                    datagram.send(requestPacket);

                }catch (NoSuchElementException nsee){
                    System.err.println("ERROR: " + nsee);
                    System.exit(0);                 
                }catch (IOException ioe){
                    System.err.println("ERROR: " + ioe);
                    System.exit(0);                 
                }

           }while(!message.equals("exit"));
           
           //encerar aplicacao
           System.exit(0);
        }


}
