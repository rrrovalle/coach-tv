package udesc.pin3.Meeting;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.graalvm.collections.Pair;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/meeting")
public class MeetingResource {

    @Inject
    MeetingService meetingService;
    @Inject
    Validator validator;

    @POST
    @Path("schedule/{mentoringId}/user/{userId}")
    public Response scheduleMeeting(
            @PathParam("mentoringId") long mentoringId, @PathParam("userId") long userId, MeetingDTO dto) {
        Set<ConstraintViolation<MeetingDTO>> violations = validator.validate(dto);

        if (violations.isEmpty()) {
            Pair<Integer, String> response = meetingService.scheduleMeeting(mentoringId, userId, dto);
            return Response.status(response.getLeft()).entity(response.getRight()).build();
        } else {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(message).build();
        }
    }

    @GET
    @Path("user/{userId}")
    public Response getMeetingsByUserId(@PathParam("userId") long userId) {
        List<MeetingDTO> meetings = meetingService.getMeetingsByUserId(userId);
        return Response.ok(meetings).build();
    }

    @GET
    @Path("mentoring/{mentoringId}")
    public Response getMeetingsByMentoringId(@PathParam("mentoringId") long mentoringId) {
        List<MeetingDTO> meetings = meetingService.getMeetingsByMentoringId(mentoringId);
        return Response.ok(meetings).build();
    }

    @GET
    @Path("join/{meetingId}/{userId}")
    public Response joinMeetingRoom(@PathParam("meetingId") long meetingId, @PathParam("userId") long userId){
        Pair<Integer, Object> response = meetingService.joinMeetingRoom(meetingId, userId);
        return Response.status(response.getLeft()).entity(response.getRight()).build();
    }

}
