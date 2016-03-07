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
public class ScoreCommand {
    final static Logger log = Logger.getLogger(ScoreCommand.class.getName());
    
    String    cmdString;
    String    action;
    UUID      gameId;
    int       playerIndex;
    String    event;
    int       dMul;
    DartValue dTarget;
    
    public ScoreCommand(String cmd) throws CompuDartException {
        parseCommandString(cmd);
    }
    
    public void parseCommandString(String cmd) throws CompuDartException {
        try {
            String[] params = cmd.split(" ");
            if(params.length == 4) {
                validateParams(params);
            }
        } catch (Exception e) {
           throw new CompuDartException("Error parsing command string: " + cmd, e);
        }
    }
    
    public void validateParams(String[] params) throws CompuDartException {
        if(params[0].equalsIgnoreCase("score") || params[0].equalsIgnoreCase("unscore")) {
            action = params[0];
        } else {
            throw new CompuDartException("Not a score command: " + params[0]);            
        }
        gameId = UUID.fromString(params[1]);
        playerIndex = Integer.parseInt(params[2]);
        if((playerIndex == 1) || (playerIndex == 2)) {
            // Player index is valid
        } else {
            throw new CompuDartException("Invalid playerIndex: " + playerIndex);
        }
        event = params[3];
        if(!isValidEvent(event)) {
            throw new CompuDartException("Invalid event: " + event);
        }
        log.info("parseCommandString(): " + this.toString());
    }
    
    public boolean isValidEvent(String e) {
        String multiplier = e.substring(0,1);
        String target = e.substring(1);
        if(multiplier.equalsIgnoreCase("S") || multiplier.equalsIgnoreCase("D") || multiplier.equalsIgnoreCase("T")) {
            if(multiplier.equalsIgnoreCase("S")) {
                dMul = 1;
            } else if(multiplier.equalsIgnoreCase("D")) {
                dMul = 2;
            } else {
                dMul = 3;
            }
        } else {
            log.info("Invalid multiplier: " + multiplier);
            return false;
        }
        DartValue dTarget = DartScoreMap.dartValue(target);
        if(dTarget == DartValue.D0) {
            log.info("Invalid dart target value: " + target);
            return false;
        }
        return true;
    }

    public String getCmdString() {
        return cmdString;
    }

    public void setCmdString(String cmdString) {
        this.cmdString = cmdString;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getdMul() {
        return dMul;
    }

    public void setdMul(int dMul) {
        this.dMul = dMul;
    }

    public DartValue getdTarget() {
        return dTarget;
    }

    public void setdTarget(DartValue dTarget) {
        this.dTarget = dTarget;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    @Override
    public String toString() {
        return "GameID: " + gameId + " Player[" + playerIndex + "] event: " + event;
    }
        
}
