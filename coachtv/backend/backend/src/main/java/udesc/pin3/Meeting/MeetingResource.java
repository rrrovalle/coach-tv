package udesc.pin3.Meeting;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.graalvm.collections.Pair;
import udesc.pin3.Mentoring.MentoringDTO;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/meeting")
public class MeetingResource {

    @Inject
    MeetingService meetingService;
    @Inject
    Validator validator;

    @POST
    @Path("schedule")
    public Response scheduleMeeting(MeetingDTO dto){
        Set<ConstraintViolation<MeetingDTO>> violations = validator.validate(dto);

        if (violations.isEmpty()) {
            Pair<Integer, String> response = meetingService.scheduleMeeting(dto);
            return Response.status(response.getLeft()).entity(response.getRight()).build();
        } else {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(message).build();
        }
    }

}
