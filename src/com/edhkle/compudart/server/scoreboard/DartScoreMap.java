/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server.scoreboard;

import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D0;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D15;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D16;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D17;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D18;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D19;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.D20;
import static com.edhkle.compudart.server.scoreboard.DART_VALUE.DC;
import java.util.HashMap;

 enum DART_VALUE {
    D0(0),
    D15(15),
    D16(16),
    D17(17),
    D18(18),
    D19(19),
    D20(20),
    DC(25);
    
    int score;
    
    DART_VALUE(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
}
/**
 *
 * @author ehansen
 */
public class DartScoreMap extends HashMap<DART_VALUE,Integer> {
    
    public DartScoreMap() {
        this.put(DART_VALUE.D15, 0);
        this.put(DART_VALUE.D16, 0);
        this.put(DART_VALUE.D17, 0);
        this.put(DART_VALUE.D18, 0);
        this.put(DART_VALUE.D19, 0);
        this.put(DART_VALUE.D20, 0);
        this.put(DART_VALUE.DC, 0);
    }

    public boolean hitDart(DART_VALUE dart) {
        int val = this.get(dart);
        this.put(dart, ++val);
        return(this.get(dart) > 3);
    }
    
    public boolean removeDart(DART_VALUE dart) {
        int val = this.get(dart);
        this.put(dart, --val);
        return(this.get(dart) >= 3);
    }
    
    public int getDart(DART_VALUE dart) {
        return this.get(dart);
    }
    
    public static DART_VALUE dartValue(String value) {
        DART_VALUE d = D0;
        if(value.equalsIgnoreCase("15")) {
            d = D15;
        }
        
        if(value.equalsIgnoreCase("16")) {
            d = D16;
        }

        if(value.equalsIgnoreCase("17")) {
            d = D17;
        }
        
        if(value.equalsIgnoreCase("18")) {
            d = D18;
        }

        if(value.equalsIgnoreCase("19")) {
            d = D19;
        }
        
        if(value.equalsIgnoreCase("20")) {
            d = D20;
        }
        
        if(value.equalsIgnoreCase("C")) {
            d = DC;
        }
        return d;
    }
}
