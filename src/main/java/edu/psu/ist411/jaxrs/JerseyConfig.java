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

package edu.psu.ist411.jaxrs;

import edu.psu.ist411.jaxrs.presentation.UsersController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Jersey.
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UsersController.class);
    }
}