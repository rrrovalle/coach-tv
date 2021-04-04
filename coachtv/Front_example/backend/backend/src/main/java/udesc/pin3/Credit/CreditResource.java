package udesc.pin3.Credit;


import io.netty.handler.codec.http.HttpResponseStatus;
import org.graalvm.collections.Pair;
import udesc.pin3.Mentoring.MentoringDTO;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/credits")
public class CreditResource {

    @Inject
    CreditService creditService;
    @Inject
    Validator validator;

    @Path("plans")
    @GET
    public Response getCreditPlans() {
        return Response.ok(creditService.getCreditPlans()).build();
    }

    @Path("purchase")
    @POST
    public Response purchaseCredits(CreditPackDTO creditPackDTO) {
        Set<ConstraintViolation<CreditPackDTO>> violations = validator.validate(creditPackDTO);
        if (violations.isEmpty()) {
            Pair<Integer, String> purchaseReturn = creditService.purchaseCredits(creditPackDTO);
            return Response.status(purchaseReturn.getLeft()).entity(purchaseReturn.getRight()).build();
        } else {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(message).build();
        }
    }

}
