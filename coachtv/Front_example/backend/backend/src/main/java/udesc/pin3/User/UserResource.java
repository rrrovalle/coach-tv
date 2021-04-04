package udesc.pin3.User;

import io.netty.handler.codec.http.HttpResponseStatus;
import udesc.pin3.ErrorMessage;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/user")
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    Validator validator;

    @POST
    @Path("register")
    public Response registerUser(UserDTO user) {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        if (violations.isEmpty()) {
            boolean success = userService.registerUser(user);
            if (success)
                return Response.ok().build();
            else
                return Response.status(HttpResponseStatus.BAD_REQUEST.code())
                        .entity("O e-mail informado já existe no sistema. Tente novamente!")
                        .build();
        } else {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(message).build();
        }
    }

    @POST
    @Path("login")
    public Response login(UserDTO dto) {
        dto = userService.login(dto);
        if (dto == null) {
            return Response
                    .status(HttpResponseStatus.BAD_REQUEST.code())
                    .entity(new ErrorMessage("E-mail ou senha incorreto. Tente novamente!"))
                    .build();
        } else {
            return Response.ok(dto).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") long id) {
        return Response.ok(userService.getUserDTOById(id)).build();
    }

}
