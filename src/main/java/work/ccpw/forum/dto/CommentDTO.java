package work.ccpw.forum.dto;

import lombok.Data;
import work.ccpw.forum.model.User;

/**
 * @program: community
 * @description:
 * @author: cone
 * @create: 2020-03-19 12:58
 **/
@Data
public class CommentDTO {

    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;

}
