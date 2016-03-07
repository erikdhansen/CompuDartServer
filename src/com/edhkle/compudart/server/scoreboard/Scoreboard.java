/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server.scoreboard;

import com.edhkle.compudart.server.ScoreCommand;
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
        i--;
        if(i < 0 || i > 1) {
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
    
    public void processScoreCommand(ScoreCommand c) throws GameOverException {
        if(c.getAction().equalsIgnoreCase("score")) {
            scoreDart(c);
            checkGameOver();
        } else if(c.getAction().equalsIgnoreCase("unscore")) {
            unscoreDart(c);
        }
    }
    
    private void scoreDart(ScoreCommand c) throws GameOverException {
        PlayerScore p = getPlayerScore(c.getPlayerIndex());
        boolean closed = getPlayerScore(getOtherPlayerId(c.getPlayerIndex())).isDartClosed(c.getdTarget());
        for(int i=0; i < c.getdMul(); i++) {
            p.addDart(c.getdTarget(), closed);
        }
    }
    
    private void unscoreDart(ScoreCommand c) {
        PlayerScore p = getPlayerScore(c.getPlayerIndex());
        boolean closed = getPlayerScore(getOtherPlayerId(c.getPlayerIndex())).isDartClosed(c.getdTarget());
        for(int i=0; i < c.getdMul(); i++) {
            p.removeDart(c.getdTarget(), closed);
        }
    }
    
    private static int getOtherPlayerId(int i) {
        return ((--i) ^ 1) + 1;
    }

    private void checkGameOver() throws GameOverException {
        boolean gameOver = false;
        if(gameOver == true) {
            throw new GameOverException();
        }
    }
}
