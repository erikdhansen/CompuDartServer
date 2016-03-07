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
    
    public void addDart(String value) {
        DartValue dart = DartScoreMap.dartValue(value);
        boolean closed = dMap.hitDart(dart);
        if(closed) {
            score += dart.getScore();
        }
    }
    
    public void removeDart(String value) {
        DartValue dart = DartScoreMap.dartValue(value);
        boolean closed = dMap.removeDart(dart);
        if(!closed && score > 0) {
            score -= dart.getScore();
        }
    }
    
    public DartScoreMap getDartMap() {
        return dMap;
    }
    
    public int getScore() {
        return score;
    }
}
