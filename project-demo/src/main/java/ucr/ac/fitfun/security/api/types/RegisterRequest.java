package ucr.ac.fitfun.security.api.types;


public record RegisterRequest(
        String name,
        String email,
        String password
) {
}

