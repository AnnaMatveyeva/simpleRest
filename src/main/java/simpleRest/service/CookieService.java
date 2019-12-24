package simpleRest.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    public void setCookie(String username, String password, HttpServletResponse response) {
        Cookie nameCookie = new Cookie("username", username);
        Cookie passCookie = new Cookie("password", password);
        //add cookie to response
        response.addCookie(nameCookie);
        response.addCookie(passCookie);
    }

    public Map<String, String> getCookies(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("username")) {
                result.put("username", cookie.getValue());
            }
            if (cookie.getName().equals("password")) {
                result.put("password", cookie.getValue());
            }
        }
        return result;
    }


}
