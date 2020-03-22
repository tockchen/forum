package work.ccpw.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.ccpw.community.dto.PaginationDTO;
import work.ccpw.community.service.QuestionService;

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
    private QuestionService QuestionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        PaginationDTO pagination = QuestionService.list(page, size);
        model.addAttribute("pagination", pagination);



        return "index";
    }
}
