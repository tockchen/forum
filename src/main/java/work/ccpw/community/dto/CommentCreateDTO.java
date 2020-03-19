package work.ccpw.community.dto;

import lombok.Data;

/**
 * @program: community
 * @description: 回答问题
 * @author: cone
 * @create: 2020-03-16 19:24
 **/
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
