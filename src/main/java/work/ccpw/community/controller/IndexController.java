package work.ccpw.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.ccpw.community.dto.QuestionDTO;
import work.ccpw.community.mapper.UserMapper;
import work.ccpw.community.model.User;
import work.ccpw.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


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

    @Autowired
    private QuestionService QuestionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);

                    }
                    break;
                }
            }
        }
        System.out.println("2");
        List<QuestionDTO> questionList = QuestionService.list();
        model.addAttribute("questions", questionList);

        return "index";
    }
}
