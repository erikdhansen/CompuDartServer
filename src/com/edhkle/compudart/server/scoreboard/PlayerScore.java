/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server.scoreboard;

/**
 *
 * @author ehansen
 */
public class PlayerScore {
    DartScoreMap dMap = new DartScoreMap();
    int score = 0;
    
    public PlayerScore() {
        
    }
    
    public void addDart(DartValue dart, boolean opponent) {
        boolean closed = dMap.hitDart(dart);
        if(closed && !opponent) {
            score += dart.getScore();
        }
    }
    
    public void removeDart(DartValue dart, boolean opponent) {
        boolean closed = dMap.removeDart(dart);
        if(closed && score > 0 && !opponent) {
            score -= dart.getScore();
        }
    }
    
    public boolean isDartClosed(DartValue dart) {
        boolean closed = false;
        if(dMap.get(dart) >= 3) {
            closed = true;
        }
        return closed;
    }
    
    public DartScoreMap getDartMap() {
        return dMap;
    }
    
    public int getScore() {
        return score;
    }
}
