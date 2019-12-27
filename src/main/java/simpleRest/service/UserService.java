package simpleRest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simpleRest.entity.User;
import simpleRest.exception.UserForbiddenException;
import simpleRest.repository.RoleRepository;
import simpleRest.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authService;

    public User userAuthentication(String username, String password, String role)
        throws UserForbiddenException {
        return authService
            .userAuthentication(username, password, role, this);
    }

    public User cookieAuthentication(String username, String password)
        throws UserForbiddenException {
        return authService.cookieAuthentication(username, password);
    }

    public User createUser(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleRepository.findByName("USER"));
        return userRepo.save(user);

    }
}