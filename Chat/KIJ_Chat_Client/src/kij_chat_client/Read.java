/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kij_chat_client;

/*import java.net.Socket;*/
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author santen-suru
 */
public class Read implements Runnable {
        
        private Scanner in;//MAKE SOCKET INSTANCE VARIABLE
        String input;
        boolean keepGoing = true;
        ArrayList<String> log;
	
	public Read(Scanner in, ArrayList<String> log)
	{
		this.in = in;
                this.log = log;
	}
    
        @Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
            try
            {
                while (keepGoing)//WHILE THE PROGRAM IS RUNNING
                {						
                    if(this.in.hasNext()) {
                        //IF THE SERVER SENT US SOMETHING
                        input = this.in.nextLine();
                        String cek = input.split(" ")[0].toLowerCase();

                        if(cek.equals("success") || cek.equals("fail")){

                        }
                        else{
//                            byte[] key = "Key".getBytes();
                            
                            String[] vals = input.split(": ");
                            byte[] key=vals[0].getBytes();
                            RC4 rc4 = new RC4(key);
//                            byte[] b = vals[1].getBytes();
//                            String decrypted = Amankan.decrypt(b, "password");
                            String decrypted = rc4.decrypt(vals[1]);

                            input = vals[0] + ": " + decrypted;
                        }


                        System.out.println(input);//PRINT IT OUT

                        if (input.split(" ")[0].toLowerCase().equals("success")) {
                            if (input.split(" ")[1].toLowerCase().equals("logout")) {
                                keepGoing = false;
                            } else if (input.split(" ")[1].toLowerCase().equals("login")) {
                                log.clear();
                                log.add("true");
                            }
                        }

                    }

                }
            }
            catch (Exception e)
            {
                    e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
            } 
	}
}
