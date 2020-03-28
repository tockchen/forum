package work.ccpw.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.ccpw.forum.dto.CommentDTO;
import work.ccpw.forum.dto.QuestionDTO;
import work.ccpw.forum.enums.CommentTypeEnum;
import work.ccpw.forum.exception.CustomizeErrorCode;
import work.ccpw.forum.exception.CustomizeException;
import work.ccpw.forum.service.CommentService;
import work.ccpw.forum.service.QuestionService;

import java.util.List;

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

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(questionId, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(questionId);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
