/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.ist411.jaxrs.domain;

import edu.psu.ist411.jaxrs.data.User;
import edu.psu.ist411.jaxrs.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Default implementation of {@link UserService}.
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    
    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public Page<User> getUsers(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUser(final long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NoSuchUserException::new);
    }

    @Override
    public User getUser(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(NoSuchUserException::new);
    }

    @Override
    public User createUser(final String email, 
            final String first, final String last) {
        if (userRepository.existsByEmail(email)) {
            throw new InvalidEmailException(email);
        }
        
        // Create the business model
        final User user = new User();
        user.setEmail(email);
        user.setFirstName(first);
        user.setLastName(last);
        
        // Save the business model
        return userRepository.save(user);
    }
}