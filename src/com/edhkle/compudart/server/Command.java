/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server;

import com.edhkle.compudart.server.scoreboard.DartScoreMap;
import com.edhkle.compudart.server.scoreboard.DartValue;
import java.util.UUID;
import java.util.logging.Logger;

/**
 *
 * @author ehansen
 */
public class Command {
    final static Logger log = Logger.getLogger(Command.class.getName());
    
    String cmdString;
    UUID   gameId;
    int    playerIndex;
    String event;
    
    public Command(String cmd) throws CompuDartException {
        parseCommandString(cmd);
    }
    
    public void parseCommandString(String cmd) throws CompuDartException {
        try {
            String[] params = cmd.split(" ");
            if(params.length == 3) {
                validateParams(params);
            }
        } catch (Exception e) {
           throw new CompuDartException("Error parsing command string: " + cmd, e);
        }
    }
    
    public void validateParams(String[] params) throws CompuDartException {
        gameId = UUID.fromString(params[0]);
        playerIndex = Integer.parseInt(params[1]);
        if((playerIndex == 1) || (playerIndex == 2)) {
            // Player index is valid
        } else {
            throw new CompuDartException("Invalid playerIndex: " + playerIndex);
        }
        event = params[2];
        if(!isValidEvent(event)) {
            throw new CompuDartException("Invalid event: " + event);
        }
        log.info("parseCommandString(): " + this.toString());
    }
    
    public static boolean isValidEvent(String e) {
        String multiplier = e.substring(0,1);
        String target = e.substring(1);
        if(multiplier.equalsIgnoreCase("S") || multiplier.equalsIgnoreCase("D") || multiplier.equalsIgnoreCase("T")) {
            // Valid multipler found
        } else {
            log.info("Invalid multiplier: " + multiplier);
            return false;
        }
        DartValue dartValue = DartScoreMap.dartValue(target);
        if(dartValue == DartValue.D0) {
            log.info("Invalid dart target value: " + target);
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "GameID: " + gameId + " Player[" + playerIndex + "] event: " + event;
    }
        
}
