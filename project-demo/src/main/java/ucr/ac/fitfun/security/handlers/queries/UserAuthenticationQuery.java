package ucr.ac.fitfun.security.handlers.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ucr.ac.fitfun.security.jpa.entities.UserEntity;
import ucr.ac.fitfun.security.jpa.repositories.UserRepository;
import ucr.ac.fitfun.security.models.AuthenticatedUser;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserAuthenticationQuery implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByEmail(username);

        if (user.isPresent()) {
            return new AuthenticatedUser(
                    user.get().getId(),
                    user.get().getEmail(),
                    user.get().getPassword(),
                    Collections.emptyList()
                  //  user.get().getRoles().stream().map(SimpleGrantedAuthority::new).toList()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

