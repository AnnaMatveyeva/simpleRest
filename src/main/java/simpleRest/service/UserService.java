package simpleRest.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import simpleRest.entity.Role;
import simpleRest.entity.User;
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

    public User getUser(Long id) {
        return userRepo.findById(id).get();
    }

    public User getUser(String username) {
        User user = new User();
        user.setName(username);
        Example<User> example = Example.of(user);
        return userRepo.findOne(example).get();
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public User save(User user) {
        userRepo.saveAndFlush(user);
        return user;
    }

    public User authentication(String username, String password, HttpServletResponse response,
        Long role_id) {
        try {
            User user = getUser(username);
            if (user.getPassword().equals(password) && user.getRole().getId().equals(role_id)) {
                cookieService.setCookie(username, password, response);
                return user;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NoSuchElementException ex) {
            return createUser(username, password, response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User createUser(String username, String password, HttpServletResponse response) {
        User user = new User();
        user.setName(username);

        try {
            user.setPassword(coder(password).toString());
            user.setRole(getRoleById(1L));
            save(user);
            cookieService.setCookie(user.getName(), user.getPassword(), response);
            return user;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] coder(String lineToCoder) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(lineToCoder.getBytes(StandardCharsets.UTF_8));
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).get();
    }
}