package simpleRest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simpleRest.entity.User;
import simpleRest.exception.UserForbiddenException;
import simpleRest.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    public User userAuthentication(String username, String password, String role,
        UserService userService)
        throws UserForbiddenException {
        User user = userRepo.findByName(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword()) && user.getRole()
            .getName()
            .equals(role)) {
            return user;
        } else if (user == null && !role.equals("ADMIN")) {
            user = userService.createUser(username, password);
            return user;
        }
        throw new UserForbiddenException();
    }

    public User cookieAuthentication(String username, String password)
        throws UserForbiddenException {
        User user = userRepo.findByName(username);
        if (user != null && user.getPassword().equals(password) && user.getRole().getName()
            .equals("USER")) {
            return user;
        } else {
            throw new UserForbiddenException();
        }
    }
}