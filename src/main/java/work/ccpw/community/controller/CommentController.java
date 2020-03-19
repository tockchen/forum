package work.ccpw.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ccpw.community.dto.CommentCreateDTO;
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
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_lOGIN);

        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentServise.insert(comment);
        return ResultDTO.okOf();

    }
}
