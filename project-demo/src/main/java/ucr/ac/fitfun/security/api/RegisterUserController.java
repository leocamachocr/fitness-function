package ucr.ac.fitfun.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ucr.ac.fitfun.security.api.types.OkResponse;
import ucr.ac.fitfun.security.api.types.RegisterRequest;
import ucr.ac.fitfun.security.handlers.commands.RegisterUserHandler;

import static ucr.ac.fitfun.security.api.types.OkResponse.OK_RESPONSE;


@RestController
@CrossOrigin
public class RegisterUserController {

    @Autowired
    private RegisterUserHandler handler;

    @PostMapping(value = "/register")
    public OkResponse register(@RequestBody RegisterRequest request) {
        handler.register(new RegisterUserHandler.Command(
                request.email(),
                request.name(),
                request.password()
        ));

        return OK_RESPONSE;
    }
}
