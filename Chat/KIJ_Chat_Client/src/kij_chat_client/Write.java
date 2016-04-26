/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kij_chat_client;

import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author santen-suru
 */
public class Write implements Runnable {
    
	private Scanner chat;
        private PrintWriter out;
        boolean keepGoing = true;
        ArrayList<String> log;
	
	public Write(Scanner chat, PrintWriter out, ArrayList<String> log)
	{
		this.chat = chat;
                this.out = out;
                this.log = log;
	}
	
	@Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
            try
            {
                while (keepGoing)//WHILE THE PROGRAM IS RUNNING
                {						
                    String input = chat.nextLine();	//SET NEW VARIABLE input TO THE VALUE OF WHAT THE CLIENT TYPED IN
                    String cek = input.split(" ")[0].toLowerCase();
                    if(cek.equals("login") || cek.equals("logout") || cek.equals("cg")){
                        out.println(input);//SEND IT TO THE SERVER
                        out.flush();//FLUSH THE STREAM
                    }
                    else if(cek.equals("pm")){
                        String[] vals = input.split(" ");
                        String messageOut = "";
                        for (int j = 2; j<vals.length; j++) {
                            messageOut += vals[j] + " ";
                        }
//                        byte[] encrypted = Amankan.encrypt(messageOut, "password");
//                        input = vals[0] + " " + new String(encrypted);   
                        
                        byte[] key = "Key".getBytes();
                        RC4 rc4 = new RC4(key);
//                        String message = "";
//                        System.out.println("Message: " + message);
                        String cipherText = rc4.encrypt(messageOut);
//                        System.out.println("Encrypted: " + cipherText);
//                        String decrypted = rc4.decrypt(cipherText);
//                        System.out.print("Decrypted: " + decrypted);
                        input = vals[0] + " " +vals[1]+" " + cipherText;
                        out.println(input);//SEND IT TO THE SERVER
                        out.flush();//FLUSH THE STREAM
                    }
                    else if(cek.equals("bm")){
//                        String[] vals = input.split(" ");
//                        String messageOut = "";
//                        for (int j = 2; j<vals.length; j++) {
//                            messageOut += vals[j] + " ";
//                        }
//                        byte[] encrypted = Amankan.encrypt(messageOut, "password");
//                        input = vals[0] + " " + vals[1] + " " + new String(encrypted);
//                        String decrypted = Amankan.decrypt(encrypted, "password");
//     
//                        System.out.println("Decrypted text: " + decrypted);
                        String[] vals = input.split(" ");
                        String messageOut = "";
                        for (int j = 2; j<vals.length; j++) {
                            messageOut += vals[j] + " ";
                        }
                        byte[] key = "Key".getBytes();
                        RC4 rc4 = new RC4(key);
                        String cipherText = rc4.encrypt(messageOut);
                        input = vals[0] + " " + cipherText;
                        
                        out.println(input);//SEND IT TO THE SERVER
                        out.flush();//FLUSH THE STREAM
                    }
                    else{
                        
                    }

                    

                    if (input.contains("logout")) {
                        if (log.contains("true"))
                            keepGoing = false;
                    }
                }
            }
            catch (Exception e)
            {
                    e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
            } 
	}

}
