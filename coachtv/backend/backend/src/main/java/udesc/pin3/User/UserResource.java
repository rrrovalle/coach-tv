package udesc.pin3.User;

import io.netty.handler.codec.http.HttpResponseStatus;

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

    @POST
    @Path("login")
    public Response login(UserDTO dto) {
        dto = userService.login(dto);
        if (dto == null) {
            return Response
                    .status(HttpResponseStatus.BAD_REQUEST.code())
                    .entity("E-mail ou senha incorreto. Tente novamente!")
                    .build();
        } else {
            return Response.ok(dto).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") long id) {
        return Response.ok(userService.getUserById(id)).build();
    }

}
