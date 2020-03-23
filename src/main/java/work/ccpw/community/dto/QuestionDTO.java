package work.ccpw.community.dto;

import lombok.Data;
import work.ccpw.community.model.User;

/**
 * @program: community
 * @description: 传输层
 * @author: cone
 * @create: 2020-03-13 19:36
 **/
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
