/*
 * EchoClient.java
 */
import java.io.*;
import java.net.*;

public class receiveThread extends Thread {
    
            
        DatagramSocket datagram;    	// socket de datagrama

        public receiveThread(DatagramSocket datagram_){
                this.datagram = datagram_;
        }

        public void run(){

                //variaveis
                DatagramPacket requestPacket;               
                byte[] buffer;              // buffer de dados
		        String message;				// mensagem enviada/recebida
                message = null;
          do {
                try{
                    //preparar pacote
                    buffer = new byte[256];
			        requestPacket = new DatagramPacket(buffer, buffer.length);
               
                    //recebendo msg
                    datagram.receive(requestPacket);

                    // imprime mensagem recebida
                    message = new String(requestPacket.getData()).trim();
                    System.out.println("Recebido ->" + message);

                }catch (SocketException se){
                    System.err.println("ERROR receive: " + se);
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
