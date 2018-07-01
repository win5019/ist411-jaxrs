/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.ist411.jaxrs.presentation;

import edu.psu.ist411.jaxrs.data.User;

/**
 * Models for the {@link UsersController}.
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
public abstract class UserModels {
    private UserModels() {}
    
    /** Request body for user creation. */
    public static final class UserCreateRequest {
        private String email;
        private String firstName;
        private String lastName;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
    
    /** View model for user. */
    public static final class UserView {
        private final long id;
        private final String firstName;
        private final String lastName;
        
        public UserView(final User user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
        }

        public long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}