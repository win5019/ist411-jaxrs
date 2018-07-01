/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.ist411.jaxrs.domain;

/**
 * Thrown whenever attempt to find a user that doesn't exist
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException() {
        super("User doesn't exist!");
    }
}