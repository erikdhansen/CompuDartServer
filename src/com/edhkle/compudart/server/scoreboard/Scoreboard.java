/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server.scoreboard;

import java.util.UUID;

/**
 *
 * @author ehansen
 */
public class Scoreboard {
    UUID gameId;
    PlayerScore[] playerScores = new PlayerScore[2];
    boolean over = false;
    
    public Scoreboard() {
        gameId = UUID.randomUUID();
        playerScores[0] = new PlayerScore();
        playerScores[1] = new PlayerScore();
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public PlayerScore getPlayerScore(int i) {
        // convert player number to index (1,2 => 0,1)
        int pNum = i--;
        if(pNum < 0 || pNum > 1) {
            return null;
        }
        return playerScores[i];
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
    
    public void scoreDart(int playerNum, String dart, int num) {
        PlayerScore p = getPlayerScore(playerNum);
        for(int i=0; i < num; i++) {
            p.addDart(dart);
        }
    }
    
    public void unscoreDart(int playerNum, String dart, int num) {
        PlayerScore p = getPlayerScore(playerNum);
        for(int i=0; i < num; i++) {
            p.removeDart(dart);
        }
    }
}
