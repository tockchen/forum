package work.ccpw.forum.mapper;

import work.ccpw.forum.model.Comment;

public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}