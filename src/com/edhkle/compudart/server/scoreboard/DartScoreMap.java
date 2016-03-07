/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server.scoreboard;

import static com.edhkle.compudart.server.scoreboard.DartValue.D0;
import static com.edhkle.compudart.server.scoreboard.DartValue.D15;
import static com.edhkle.compudart.server.scoreboard.DartValue.D16;
import static com.edhkle.compudart.server.scoreboard.DartValue.D17;
import static com.edhkle.compudart.server.scoreboard.DartValue.D18;
import static com.edhkle.compudart.server.scoreboard.DartValue.D19;
import static com.edhkle.compudart.server.scoreboard.DartValue.D20;
import static com.edhkle.compudart.server.scoreboard.DartValue.DC;
import java.util.HashMap;


/**
 *
 * @author ehansen
 */
public class DartScoreMap extends HashMap<DartValue,Integer> {
    
    public DartScoreMap() {
        this.put(DartValue.D15, 0);
        this.put(DartValue.D16, 0);
        this.put(DartValue.D17, 0);
        this.put(DartValue.D18, 0);
        this.put(DartValue.D19, 0);
        this.put(DartValue.D20, 0);
        this.put(DartValue.DC, 0);
    }

    public boolean hitDart(DartValue dart) {
        int val = this.get(dart);
        this.put(dart, ++val);
        return(this.get(dart) > 3);
    }
    
    public boolean removeDart(DartValue dart) {
        int val = this.get(dart);
        this.put(dart, --val);
        return(this.get(dart) >= 3);
    }
    
    public int getDart(DartValue dart) {
        return this.get(dart);
    }
    
    public static DartValue dartValue(String value) {
        DartValue d = D0;
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
    
    public boolean isClosed(DartValue d) {
        return (this.get(d) >= 3);
    }
}
