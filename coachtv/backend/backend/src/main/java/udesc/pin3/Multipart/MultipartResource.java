package udesc.pin3.Multipart;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/upload")
public class MultipartResource {

    @Inject
    MultipartService multipartService;

    @Path("user/profile")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response setUserProfileImage(@MultipartForm MultipartBody data){
        multipartService.setUserProfileImage(data);
        return Response.ok().build();
    }

}
