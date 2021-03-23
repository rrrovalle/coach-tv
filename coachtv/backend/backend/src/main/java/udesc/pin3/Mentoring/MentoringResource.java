package udesc.pin3.Mentoring;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api/mentoring")
public class MentoringResource {

    @Inject
    MentoringService mentoringService;

    @POST
    @Path("register")
    public Response registerMentoring(MentoringDTO mentoringDTO){
        mentoringService.registerMentoring(mentoringDTO);
        return Response.ok().build();
    }

}
