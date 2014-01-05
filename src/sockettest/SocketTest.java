/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sockettest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Joseph
 */
public class SocketTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostname = args[0];
        int portNum = Integer.parseInt(args[1]);
        
        try (
            Socket echoSocket = new Socket(hostname, portNum);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            )
        {
            String fromServer, fromUser;
            while((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
}
