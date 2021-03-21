package udesc.pin3.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/user")
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("register")
    public Response registerUser(UserDTO user) {
        userService.registerUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") long id) {
        return Response.ok(userService.getUserById(id)).build();
    }

}
