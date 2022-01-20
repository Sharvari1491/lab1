/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Amit
 */
import java.io.*;
import java.net.*;
import java.util.*;
class UDPServer {
    
    
    public static void main(String args[]) throws Exception
    {
 BufferedReader inFromUser = 
                new BufferedReader(new InputStreamReader(System.in));

 Scanner sc=new Scanner(System.in);     
DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Create DatagramSocket at server end on specified port 9876: "+
                serverSocket.getLocalSocketAddress());
        
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        char a[]=new char[94];
              int b=32;
         for(int i=0;i<94;i++)
        {

            a[i]=(char) b;
            b=b+1;
        }   

        System.out.println("Prepare send/receive buffers byte[]");
        
        while(true)
        {
            DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Make receive DatagramPacket(..)");
            
            serverSocket.receive(receivePacket);
            System.out.println("Recceive the datagram packet UDP socket.receive()");

            DatagramPacket receivePacket1 =
                new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Make receive DatagramPacket(..)");
            
            serverSocket.receive(receivePacket1);
            System.out.println("Recceive the datagram packet UDP socket.receive()");
            
            
            System.out.println("Query the received datagram packet for sockAddress"
                    + ".. getSocketAddress(): "+ receivePacket.getSocketAddress());
            
            String sentence = new String(receivePacket.getData());

             String sentence2 = new String(receivePacket1.getData());
                char c1[]=new char[sentence.length()];
               c1=sentence.toCharArray();
                char c2[]=new char[sentence2.length()];

               c2=sentence2.toCharArray();
int rot=2;
int len=sentence.length();
int o;
char[] respermt=new char[sentence.length()];

           for(int n=0;n<len;n++)
         {
             o=(n+rot)%len;

                 respermt[n]=c1[o];

         }
		System.out.println();
         

          System.out.println("reverse of permutation");
            for(int n=0;n<len;n++)
            {
               System.out.print(respermt[n]);
            }
          
	System.out.println();
         
          System.out.println("decryption(original plaintext)");

          char[] pt=new char[len];


        for(int l=0;l<len;l++)
         {
            for(int m=0;m<len;m++)
            {
                while(a[m]!=respermt[l])
                {
                    m++;
                }
                int pos1=m;

                m=0;
                while(a[m]!=c2[l])
                {
                    m++;
                }
                int pos2=m;
                int pos3=(pos1-pos2)%94;
                if (pos3<0)
                {
                    pos3=pos3+94;
                }
                pt[l]=a[pos3];
                break;
            }
         }
	System.out.println();
         
        System.out.println("result");
            for(int n=0;n<len;n++)
            {
               System.out.print(pt[n]);
            }

            System.out.println("Client sent.. getdata(): "+ sentence);
            
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            
            //String capitalizedSentence = sentence.toUpperCase();
            //System.out.println("Convert lower case character to upper case: "+capitalizedSentence);
            
            //sendData = capitalizedSentence.getBytes();
            
            System.out.println("Make a DatagramPacket to send data "
                    + "specifying socketAddress from Received DatagramPacket: "+
                    receivePacket.getSocketAddress());
           // DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
            
          //  System.out.println("Send the datagram packet");
            
            //serverSocket.send(sendPacket);
        }
    }
}

