/*
 * EchoClient.java
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class simpleChat {
    
    public static void main(String[] args) {
        
        DatagramSocket datagram;    	// socket de datagrama
        InetAddress address;        	// endereco maquina destino
        int remotePort;                 //nro da porta da maquina destino
        int localPort;                  //nro da porta da maquina local

        sendThread sThread;             //para envio de msg
        receiveThread rThread;          //para recepcao de msg

        //verificar numero de args na chamada
        if (args.length != 3){
            System.err.println("Wrong number of arguments!!!");
            System.err.println("Java simpleChat <ip_remote> <remote_port> <local_port>");
            System.exit(0);
        } else {       
            try{            
                // extrair os arg da chamada, por metodos estaticos
                address = InetAddress.getByName(args[0]);
                remotePort = Integer.parseInt(args[1]);
                localPort = Integer.parseInt(args[2]);

                // abre um socket
                datagram = new DatagramSocket(localPort);

                //instanciar threads de envio/recepcao
                sThread = new sendThread(datagram, address, remotePort);
                rThread = new receiveThread(datagram);

                //inicio da execucao das threads
                sThread.start();
                rThread.start();
                
            }catch(UnknownHostException uhe){
                System.err.println("ERROR destino desconhecido: " + uhe);
                System.exit(0);    
            }catch(NumberFormatException nfe){
                System.err.println("ERROR conversao nro de porta: " + nfe);    
                System.exit(0);
            }catch(SocketException se){
                System.err.println("ERROR ao abrir o socket: " + se);
                System.exit(0);    
            }// final do try  
            
        }// final do if-else
        
    }// final da main

}// final class
