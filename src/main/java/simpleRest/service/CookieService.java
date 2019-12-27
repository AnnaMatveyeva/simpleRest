package simpleRest.service;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import simpleRest.exception.UserForbiddenException;

@Service
public class CookieService {

    public void setCookie(String username, String password, HttpServletResponse response) {
        Cookie nameCookie = new Cookie("username", username);
        Cookie passCookie = new Cookie("password", password);
        //add cookie to response
        response.addCookie(nameCookie);
        response.addCookie(passCookie);
    }

    public Map<String, String> getCookies(HttpServletRequest request)
        throws UserForbiddenException {
        Map<String, String> result = new HashMap<>();
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("username")) {
                    result.put("username", cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    result.put("password", cookie.getValue());
                }
            }
            return result;
        } else {
            throw new UserForbiddenException("User forbidden");
        }
    }


}
