package work.ccpw.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import work.ccpw.community.mapper.UserMapper;
import work.ccpw.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @program: community
 * @description: thymeleaf, 前后端交互
 * @author: cone
 * @create: 2020-03-05 20:16
 **/
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                System.out.println(cookie.getName());
                String token = cookie.getValue();
                System.out.println(token);
                User user = userMapper.findByToken(token);
                System.out.println(user);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                    
                }
                    break;
            }
        }
        return "index";
    }
}
