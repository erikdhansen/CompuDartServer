/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server;

import java.io.Console;
import java.util.logging.Logger;

/**
 *
 * @author ehansen
 */
public class Server {
    final static Logger log = Logger.getLogger(Server.class.getName());
    static Console c = System.console();
    
    public Server() {
    }
    
    public void processCommand(Command c) {
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server();
        Command command;
        while(true) {
            String input = c.readLine();
            if(input.equalsIgnoreCase("quit")) {
                break;
            } else {
                command = new Command(input);
            }
            server.processCommand(command);
        }
        c.printf("Bye");
    }
    
}
