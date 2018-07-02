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

package edu.psu.ist411.domain;

import edu.psu.ist411.data.User;
import org.springframework.data.domain.Pageable;
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
     * @param pageable {@link Pageable}.
     * @return Page of {@link User}.
     */
    Page<User> getUsers(Pageable pageable);

    /**
     * Gets a user using their unique identifier.
     * @param userId Unique id of user.
     * @return {@link User}.
     */
    User getUser(long userId);

    /**
     * Gets a user using their unique email address.
     * @param email Unique email of user.
     * @return {@link User}.
     */
    User getUser(String email);

    /**
     * Creates and saves a new user.
     * @param email Email of user.
     * @param first First name of user.
     * @param last Last name of user.
     * @return {@link User}.
     *
     * @throws InvalidEmailException if user with email already exists.
     */
    User createUser(String email, String first, String last);

    /**
     * Updates an existing user.
     * @param userId Unique ID of user.
     * @param email Email of user.
     * @param first First name of user.
     * @param last Last name of user.
     * @return {@link User}.
     *
     * @throws InvalidEmailException if user with email already exists.
     */
    User updateUser(long userId, String email, String first, String last);
}