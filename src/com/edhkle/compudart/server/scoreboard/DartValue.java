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
public enum DartValue {
    D0(0),
    D15(15),
    D16(16),
    D17(17),
    D18(18),
    D19(19),
    D20(20),
    DC(25);
    
    int score;
    
    DartValue(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
}