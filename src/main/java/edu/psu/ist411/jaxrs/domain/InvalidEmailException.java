/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.ist411.jaxrs.domain;

/**
 * Thrown whenever an email is invalid.
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(final String email) {
        super("Email '" + email + "' already exists!");
    }
}