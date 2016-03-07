/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server;

import com.edhkle.compudart.server.scoreboard.Scoreboard;
import java.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ehansen
 */
public class Server {
    final static Logger log = Logger.getLogger(Server.class.getName());
    static Console c = System.console();
    Scoreboard scoreboard;
    
    public Server() {
        scoreboard = new Scoreboard();
        
    }
    
    public void processCommand(Command c) {
        log.info("Processing command: " + c.toString());
    }

    public static String prompt() {
        c.printf("CompuDartServer> ");
        return c.readLine();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server();
        Command command = null;
        while(true) {
            String input = prompt();
            if(input.equalsIgnoreCase("quit")) {
                c.printf("Bye\n");
                System.exit(0);
            } else {
                try {
                    command = new Command(server.scoreboard.getGameId().toString().concat(" ").concat(input));
                    server.processCommand(command);
                } catch (CompuDartException e) {
                    log.log(Level.SEVERE, "Invalid command!", e);
                }
            }
        }
    }
    
}
