package work.ccpw.forum.dto;

import lombok.Data;

/**
 * @program: community
 * @description: github用户字段
 * @author: cone
 * @create: 2020-03-07 19:17
 **/
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
