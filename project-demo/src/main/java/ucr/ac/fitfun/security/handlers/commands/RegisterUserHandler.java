package ucr.ac.fitfun.security.handlers.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ucr.ac.fitfun.exceptions.BusinessException;
import ucr.ac.fitfun.exceptions.InvalidInputException;
import ucr.ac.fitfun.security.jpa.entities.UserEntity;
import ucr.ac.fitfun.security.jpa.repositories.UserRepository;


@Component
public class RegisterUserHandler {
    @Autowired
    private UserRepository repository;

    public record Command(String email, String name, String password) {
    }

    public void register(Command command) {
        validateRequiredFields(command);
        validateExistingUser(command.email());
        UserEntity user = new UserEntity();
        user.setEmail(command.email());
        user.setName(command.name());
        user.setPassword(command.password()); // DON'T DO THIS
        repository.save(user);
    }

    private void validateExistingUser(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new BusinessException("User already exists");
        }
    }

    private void validateRequiredFields(Command command) {
        if (command.email() == null) {
            throw new InvalidInputException("email");
        }
        if (command.name() == null) {
            throw new InvalidInputException("name");
        }
        if (command.password() == null) {
            throw new InvalidInputException("password");
        }


    }
}
