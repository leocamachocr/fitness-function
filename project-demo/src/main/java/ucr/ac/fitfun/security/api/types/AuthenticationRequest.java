package ucr.ac.fitfun.security.api.types;


public record AuthenticationRequest(
        String username,
        String password
) {
}