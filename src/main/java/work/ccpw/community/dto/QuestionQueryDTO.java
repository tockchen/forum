package work.ccpw.community.dto;

import lombok.Data;

/**
 * @program: community
 * @description:
 * @author: cone
 * @create: 2020-03-23 14:42
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
