package work.ccpw.community.mapper;

import work.ccpw.community.model.Question;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
}