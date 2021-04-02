package udesc.pin3.Multipart;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Path("/api/multipart")
public class MultipartResource {

    @Inject
    MultipartService multipartService;

    @Path("upload/user/profile/{userId}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response setUserProfileImage(@PathParam("userId") long userId, @MultipartForm MultipartBody data) {
        boolean success = multipartService.setUserProfileImage(userId, data);
        if (success)
            return Response.ok().build();
        else
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).build();
    }

    @GET
    @Path("download/user/profile/{userId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadUserProfileImage(@PathParam("userId") long userId) {
        MultipartBody body = multipartService.downloadUserProfileImage(userId);
        return Response.ok().entity(body.file).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                body.fileName + "\"").build();
    }

    @Path("upload/mentoring/preview/{mentoringId}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response setMentoringPreview(@PathParam("mentoringId") long mentoringId, @MultipartForm MultipartBody data) {
        boolean success = multipartService.setMentoringPreview(mentoringId, data);
        if (success)
            return Response.ok().build();
        else
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).build();
    }

    @GET
    @Path("download/mentoring/preview/{mentoringId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadMentoringPreview(@PathParam("mentoringId") long mentoringId) {
        MultipartBody body = multipartService.downloadMentoringPreview(mentoringId);
        return Response.ok().entity(body.file).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                body.fileName + "\"").build();
    }

}
