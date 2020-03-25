package work.ccpw.community.mapper;

import work.ccpw.community.model.Comment;

public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}