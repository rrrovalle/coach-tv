package udesc.pin3.Mentoring;

import io.netty.handler.codec.http.HttpResponseStatus;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/mentoring")
public class MentoringResource {

    @Inject
    MentoringService mentoringService;
    @Inject
    Validator validator;

    @POST
    @Path("register")
    public Response registerMentoring(MentoringDTO mentoringDTO) {
        Set<ConstraintViolation<MentoringDTO>> violations = validator.validate(mentoringDTO);
        if (violations.isEmpty()) {
            mentoringService.registerMentoring(mentoringDTO);
            return Response.ok().build();
        } else {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(message).build();
        }
    }

}
