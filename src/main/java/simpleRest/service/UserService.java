package simpleRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import simpleRest.entity.User;
import simpleRest.exception.UserForbiddenException;
import simpleRest.repository.RoleRepository;
import simpleRest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User save(User user) {
        userRepo.saveAndFlush(user);
        return user;
    }

    public User userAuthentication(String username, String password, String role)
        throws UserForbiddenException {
        User user = userRepo.findByName(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword()) && user.getRole()
            .getName()
            .equals(role)) {
            return user;
        } else if (user == null) {
            user = createUser(username, password);
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

    public User createUser(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleRepository.findByName("USER"));
        save(user);
        return user;

    }
}