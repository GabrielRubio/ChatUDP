/*
 * EchoServer.java
 */
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class ChatServer {
    
    public static void main(String[] args) {
		// declaracao variaveis
        DatagramSocket datagram;    	// socket de datagrama
        DatagramPacket requestPacket;   // pacote sendo enviado
        DatagramPacket responsePacket;  // pacote sendo recebido
        Scanner stdIn;       		// leitura de dados
        byte[] buffer;                 	// buffer de dados
		String message;					// mensagem recebida/enviada
		Date data; 						//data e hora atual
        InetAddress address;   			//endereco da maquina origem
        int port;						// nro porta maquina origem

		try{
            // abre um socket
            datagram = new DatagramSocket(1050);
 			
	        System.out.println("Chat aberto...");
 			while(true){
	            // recebe mensagem
	            buffer = new byte[256];
				requestPacket = new DatagramPacket(buffer, buffer.length);
	            datagram.receive(requestPacket);

	            // imprime mensagem recebida
            	message = new String(requestPacket.getData()).trim();
            	System.out.println("Resposta -> " + message);

	            //extrai address e port
	            address = requestPacket.getAddress();
	            port = requestPacket.getPort();
	            
                // le mensagem
	            stdIn = new Scanner(System.in);
    	        System.out.print("Mensagem -> ");
        	    message = stdIn.nextLine();
			
	            // envia resposta
	            buffer = message.getBytes();
	            responsePacket = new DatagramPacket(buffer, buffer.length, address, port);
	            datagram.send(responsePacket);
	            // System.out.println("Resposta enviada!");
	 		}
        } catch(IOException e){
            System.err.println("ERROR: " + e);
        }        
    }
}
