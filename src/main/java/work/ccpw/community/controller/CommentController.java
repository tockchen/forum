package work.ccpw.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import work.ccpw.community.dto.CommentCreateDTO;
import work.ccpw.community.dto.CommentDTO;
import work.ccpw.community.dto.ResultDTO;
import work.ccpw.community.enums.CommentTypeEnum;
import work.ccpw.community.exception.CustomizeErrorCode;
import work.ccpw.community.model.Comment;
import work.ccpw.community.model.User;
import work.ccpw.community.service.CommentServise;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        comment.setLikeCount(0L);
        commentServise.insert(comment,user);
        return ResultDTO.okOf();

    }


    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){


        List<CommentDTO> commentDTOS = commentServise.listByTargetId(id, CommentTypeEnum.COMMENT);

        return ResultDTO.okOf(commentDTOS);
    }

}
