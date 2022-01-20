/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cs;

/**
 *
 * @author admin
 */
import java.net.Socket;
import java.net.*;
import java.lang.*;
import java.io.*;
import java.io.IOException;
import java.util.*;



class Main
{
	public static void main(String args[])throws Exception
	{

		Socket s1=new Socket(InetAddress.getLocalHost(),7000);
		/*BufferedReader in;
            in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		System.out.println(in.readLine());*/



                 char a[]=new char[94];
              int b=32;



         Scanner sc=new Scanner(System.in);
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
            for(int m=0;m<94;m++)
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
         System.out.println("step1-cipher text after substitution");
         for(i=0;i<len;i++)
         {
            System.out.print(ct[i]);

         }
         int o;
         System.out.println("step 2-Permutation:enter the key to rotate the string");
         //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         Scanner sc1=new Scanner(System.in);
         int rot=sc1.nextInt();
         char[] permt=new char[s.length()];
         for(int n=0;n<len;n++)
         {
             o=(n+rot)%s.length();

                 permt[o]=ct[n];

         }


          System.out.println("permutation of ciphertext");
            for(int n=0;n<len;n++)
            {
               System.out.print(permt[n]);
            }

            PrintStream pr=new PrintStream(s1.getOutputStream());
            
            String ci="";
             for (int si = 0; si < permt.length; si++) {
             ci=ci+permt[si];
            
  
}
            pr.println(ci);
            String ci1="";
             for (int si = 0; si < key1.length; si++) {
             ci1=ci1+key1[si];
            
  
}
            // String ci1=Arrays.toString(key1);
            pr.println(ci1);
            pr.println(rot);

		s1.close();
            }

	}






