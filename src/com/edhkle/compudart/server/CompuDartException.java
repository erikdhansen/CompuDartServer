/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edhkle.compudart.server;

/**
 *
 * @author ehansen
 */
class CompuDartException extends Exception {
    
    public CompuDartException(String message) {
        super(message);
    }
    
    public CompuDartException(String message, Throwable t) {
        super(message, t);
    }
    
}
