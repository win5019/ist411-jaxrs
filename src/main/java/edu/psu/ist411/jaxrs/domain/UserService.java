/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.ist411.jaxrs.domain;

import edu.psu.ist411.jaxrs.data.User;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;

/**
 * Business-layer abstraction for users.
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
public interface UserService {
    /**
     * Paginates all available users.
     * @param pageable {@link Pageable}
     * @return Page of {@link User}
     */
    Page<User> getUsers(Pageable pageable);
    
    /**
     * Gets a user using their unique identifier.
     * @param userId Unique id of user
     * @return {@link User}
     */
    User getUser(long userId);
    
    /**
     * Gets a user using their unique email address.
     * @param email Unique email of user
     * @return {@link User}
     */
    User getUser(String email);
    
    /**
     * Creates and saves a new user.
     * @param email Email of user
     * @param first First name of user
     * @param last Last name of user
     * @return {@link User}
     */
    User createUser(String email, String first, String last);
}