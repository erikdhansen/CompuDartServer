/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server;

import com.edhkle.compudart.server.scoreboard.Scoreboard;
import com.edhkle.compudart.server.scoreboard.GameOverException;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ehansen
 */
public class Server {
    final static Logger log = Logger.getLogger(Server.class.getName());
    
    static Server me = null;
    static Console c = System.console();
    Map<UUID,Scoreboard> games = new HashMap<>();
    
    public Server() throws CompuDartException {
        if(me != null) {
            throw new CompuDartException("Server already instantiated.");
        }
        me = this;
    }
    
    public UUID addNewGame() {
        Scoreboard s = new Scoreboard();
        games.put(s.getGameId(), s);
        return s.getGameId();
    }
    
    public Scoreboard getScoreboard(UUID gameId) {
        return games.get(gameId);
    }
    
    public boolean processCommand(String s) {
        boolean isScoreCommand = false;
        String[] p = s.split(" ");
        if(p.length > 0) {
            if(p[0].equalsIgnoreCase("score")) {
                isScoreCommand = true;
            } else if (p[0].equalsIgnoreCase("games")) {
                // games list
                for(UUID u : games.keySet()) {
                    c.printf("%s\n", u.toString());
                }
            }
        } else {
            // single word command
        }
        return isScoreCommand;
    }
    
    public void processScoreCommand(ScoreCommand c) throws GameOverException {
        log.info("processScoreCommand: " + c.toString());
        Scoreboard s = games.get(c.getGameId());
        s.processScoreCommand(c);
    }

    public static String prompt() {
        c.printf("CompuDartServer> ");
        return c.readLine();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        UUID gameId = server.addNewGame();
        while(gameId != null) {
            String input = prompt();
            if(input.equalsIgnoreCase("quit")) {
                c.printf("Bye\n");
                System.exit(0);
            } else {
                try {
                    if(server.processCommand(input)) {
                        ScoreCommand scoreCommand = new ScoreCommand(input);
                        server.processScoreCommand(scoreCommand);                        
                    } else {
                        
                    }
                } catch (GameOverException e) {
                    c.printf("Game " + gameId + " over!!!");
                    Scoreboard finalScore = server.games.get(gameId);
                    server.games.remove(gameId);
                    gameId = null;
                } catch (CompuDartException e) {
                    log.log(Level.SEVERE, "Invalid command!", e);
                }
            }
        }
    }
    
}
