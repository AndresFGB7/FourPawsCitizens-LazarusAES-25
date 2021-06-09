package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.filters;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.services.UserAppService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.*;

@Provider
@Logged
//@PreMatching
public class SecutiryFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        try {

            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

            if (authHeader.size() > 0) {

                // Extracting credentials from header
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = new String(Base64.getDecoder().decode(authToken));
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                // Validating credentials
                Optional<String> role = new UserAppService().validateUser(username, password);
                if(role.isPresent()) {
                    requestContext.getHeaders().add("role", role.get());
                    return;
                } else {
                    requestContext.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid credentials")
                            .build());
                }

            } else {

            }

        } catch (NullPointerException e) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Credentials not provided")
                    .build());
        }

    }

}
