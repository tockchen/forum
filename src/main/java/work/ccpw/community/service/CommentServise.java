package work.ccpw.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.ccpw.community.enums.CommentTypeEnum;
import work.ccpw.community.exception.CustomizeErrorCode;
import work.ccpw.community.exception.CustomizeException;
import work.ccpw.community.mapper.CommentMapper;
import work.ccpw.community.mapper.QuestionExtMapper;
import work.ccpw.community.mapper.QuestionMapper;
import work.ccpw.community.model.Comment;
import work.ccpw.community.model.Question;

/**
 * @program: community
 * @description: 评论
 * @author: cone
 * @create: 2020-03-17 11:09
 **/
@Service
public class CommentServise {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {

            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {


            throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {

            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
        } else {

            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());

            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTON_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
