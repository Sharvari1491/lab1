/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Amit
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;
class UDPClient {
    public static void main(String args[]) throws Exception
    {
        //Read the input string from user's I/O 
        BufferedReader inFromUser = 
                new BufferedReader(new InputStreamReader(System.in));
        //Read the input from the user.
        //System.out.println("Type a string in lower case:");
 Scanner sc=new Scanner(System.in);
        //adding of the characters in an array
         char a[]=new char[94];
              int b=32;
         for(int i=0;i<94;i++)
        {

            a[i]=(char) b;
            b=b+1;
        }


        System.out.println("enter the plain text:");

        String s=sc.nextLine();


        //char c[]=new char[s.length()];
       int len=s.length();
         char[] p=s.toCharArray();

         System.out.println("enter the key" );
         String key=sc.nextLine();
         char[] k=key.toCharArray();

         //int k[]=new int[s.length()];

         char[] key1=new char[s.length()];
         char[] ct=new char[s.length()];

         int i,j;
         i=0;
         j=0;
         while(i<s.length())
         {
                key1[i]=k[j];
                i++;
                j++;
                if (j==key.length())
                {
                    j=0;

                }
         }
          System.out.println("key");
         for(i=0;i<len;i++)
         {
            System.out.print(key1[i]);

         }

         for(int l=0;l<len;l++)
         {
            for(int m=0;m<len;m++)
            {
                while(a[m]!=p[l])
                {
                    m++;
                }
                int pos1=m;

                m=0;
                while(a[m]!=key1[l])
                {
                    m++;
                }
                int pos2=m;
                int pos3=(pos1+pos2)%94;
                ct[l]=a[pos3];
                break;
            }
         }
		System.out.println();
         System.out.println("step1-cipher text after substitution");
         for(i=0;i<len;i++)
         {
            System.out.print(ct[i]);

         }
         int o;
       System.out.println();
         
         System.out.println("step 2-Permutation:enter the key to rotate the string");
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         int rot=Integer.parseInt(br.readLine());
         char[] permt=new char[s.length()];
         for(int n=0;n<len;n++)
         {
             o=(n+rot)%s.length();
           
                 permt[o]=ct[n];
             
         }
		System.out.println();
         

          System.out.println("permutation of ciphertext");
            for(int n=0;n<len;n++)
            {
               System.out.print(permt[n]);
            }
	String userInput=permt.toString();

        //String user = inFromUser.readLine();
        //System.out.println("user input: "+userInput);
        
        //Create client UDP socket
        
        DatagramSocket clientSocket = new DatagramSocket();
        System.out.println("Create the Client DatagramSocket at: "
                +clientSocket.getLocalSocketAddress());
        
        //Prepare the data (in bytes) to be sent to the client. 
        //No Input/Output Streams or filters so type information is not conveyed.
        byte[] sendData = new byte[1024];
	byte[] key2 = new byte[1024];
	//int[] key3 = new byte[1024];
       
       // byte[] receiveData = new byte[1024];

        sendData = userInput.getBytes();
	key2=key.getBytes();
	//int rot1=rot;
	//key3=rot1.getBytes();
        System.out.println("Prepare application send/receive buffer variables (byte[])");
        
        //Specify the IP address of the remote server to send the request datgram packet
        InetAddress IPAddress =
                InetAddress.getByName("localhost");
        System.out.println("Specify the IP address of the 'remote' udp server");
        
        //Make a DatagramPacket that contains userData, IP and Port numbers of destination
        DatagramPacket sendUDPPacket =
            new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      DatagramPacket sendUDPPacket1 =
            new DatagramPacket(key2, key2.length, IPAddress, 9876);
	
      /*DatagramPacket sendUDPPacket2 =
            new DatagramPacket(rot1,"1",IPAddress, 9876);*/
//DataOutputStream out;

    //create write stream to send information
   /* out=new DataOutputStream(clientSocket.getOutputStream());
	out.writeInt(rot1);*/
        System.out.println("Make the client DatagramPacket specifying IP and Port: "
                + sendUDPPacket.getSocketAddress());
        System.out.println("ALTERNATE METHOD: USE connect() to specify destination SocketAddr");
        System.out.println("in which case there is no need to specify destination IP and Port in DatagramPacket(..) constructor");
        
        //Send the user Datagram
        clientSocket.send(sendUDPPacket); 
	
        clientSocket.send(sendUDPPacket1);    
        System.out.println("Send the datagram packet: send()");
        
        /*Make a DatagramPacket to receive the response from the server//DatagramPacket receiveUDPPacket =new DatagramPacket(receiveData, receiveData.length);//System.out.println("Make a DatagramPacket to receive response from server");*/
        
        //Receive the UDP server's response. //clientSocket.receive(receiveUDPPacket);//System.out.println("Receive response: receive():");
        
        //Query the receiveUDPPacket using getData() to get the response string
       // String modifiedSentence = new String(receiveUDPPacket.getData());
        
        //System.out.println("Response FROM SERVER (modified String):" + modifiedSentence);
        
       // System.out.println("Query Response DatagramPacket from server"+ receiveUDPPacket.getSocketAddress());
        
        //Close the client socket        
        clientSocket.close();
    }
}
