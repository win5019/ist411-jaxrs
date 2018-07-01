/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.ist411.jaxrs.presentation;

import edu.psu.ist411.jaxrs.data.User;
import edu.psu.ist411.jaxrs.domain.UserService;
import edu.psu.ist411.jaxrs.presentation.UserModels.UserView;
import edu.psu.ist411.jaxrs.presentation.UserModels.UserCreateRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Presentation-layer abstraction for users API.
 * 
 * @author Tyler Suehr
 * @author David Wong
 * @author Steven Weber
 * @author Win Ton
 */
@Component
@Path("/api/users")
public class UsersController {
    private final UserService userService;
    
    
    @Autowired
    public UsersController(final UserService userService) {
        this.userService = userService;
    }
    
    @POST
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@PathParam("userId") long userId, UserCreateRequest body) {
        // Store and create the user
        final User user = userService.createUser(
                body.getEmail(), 
                body.getFirstName(), 
                body.getLastName());
        
        // Return the presentation-layer view as JSON
        return Response.status(200).entity(new UserView(user)).build();
    }
}