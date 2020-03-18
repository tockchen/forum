package work.ccpw.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.ccpw.community.dto.QuestionDTO;
import work.ccpw.community.service.QuestionService;

/**
 * @program: community
 * @description: 问题详情
 * @author: cone
 * @create: 2020-03-15 09:58
 **/
@Controller
public class QuesstionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){

        QuestionDTO questionDTO = questionService.getById(id);

        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
