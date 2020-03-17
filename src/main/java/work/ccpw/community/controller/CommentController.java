package work.ccpw.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ccpw.community.dto.CommentDTO;
import work.ccpw.community.dto.ResultDTO;
import work.ccpw.community.exception.CustomizeErrorCode;
import work.ccpw.community.model.Comment;
import work.ccpw.community.model.User;
import work.ccpw.community.service.CommentServise;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description: 评论
 * @author: cone
 * @create: 2020-03-16 19:10
 **/
@Controller
public class CommentController {


    @Autowired
    private CommentServise commentServise;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_lOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());

        commentServise.insert(comment);
        return ResultDTO.okOf();

    }
}
