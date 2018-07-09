/*
 * Copyright 2018 Group 5.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

        // Create the business model.
        final User user = new User();
        user.setEmail(email);
        user.setFirstName(first);
        user.setLastName(last);

        // Save the business model.
        return userRepository.save(user);
    }

    @Override
    public User updateUser(
        final long userId,
        final String email,
        final String first,
        final String last
    ) {
        if (userRepository.existsByEmail(email)) {
            throw new InvalidEmailException(email);
        }

        // Update the business model
        final User user = getUser(userId);
        user.setEmail(email);
        user.setFirstName(first);
        user.setLastName(last);

        // Save the business model.
        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(
        final long userId

    ) {

        // Check if the user exists.
        if (userRepository.existsById(userId)) {
            // Delete the user if the user exists.
            userRepository.deleteById(userId);
        } else {
            // If the user does not exist then throw an exception.
            throw new NoSuchUserException();
        }

    }
}