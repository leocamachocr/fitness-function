package ucr.ac.fitfun.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthenticatedUser extends User {

    private final UUID id;

    public AuthenticatedUser(
            UUID id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.id = id;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> user = new HashMap<>();

        user.put("id", id.toString());
        user.put("email", getUsername());
        user.put("roles", getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        return user;

    }
}
