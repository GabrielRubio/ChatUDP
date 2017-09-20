/*
 * EchoClient.java
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    
    public static void main(String[] args) {
        
        DatagramSocket datagram;    	// socket de datagrama
        InetAddress address;        	// endereco maquina destino
        DatagramPacket requestPacket;   // pacote sendo enviado
        DatagramPacket responsePacket;  // pacote sendo recebido
        
	    Scanner stdIn;       		// leitura de dados
	    byte[] buffer;              // buffer de dados
	    String message;				// mensagem enviada/recebida
        
        if (args.length != 1){
            System.err.println("Wrong number of arguments!!!");
            System.exit(0);
        }
            
        try{
           // abre um socket
           datagram = new DatagramSocket();

          while(true){
             // le mensagem
             stdIn = new Scanner(System.in);
             System.out.print("Mensagem -> ");
             message = stdIn.nextLine();

             // envia mensagem
             buffer = message.getBytes();
             address = InetAddress.getByName(args[0]);
             requestPacket = new DatagramPacket(buffer, buffer.length, address, 1050);
             datagram.send(requestPacket);
             // System.out.println("Mensagem enviada!");
      
             // recebe resposta
             buffer = new byte[256];
             responsePacket = new DatagramPacket(buffer, buffer.length);
             // System.out.println("Aguardando resposta...");
             datagram.receive(responsePacket);

             // imprime resposta
             message = new String(responsePacket.getData()).trim();
             System.out.println("Resposta -> " + message);
          }    
          
        } catch(IOException e){
            System.err.println("ERROR: " + e);
        }
    }
}
