package work.ccpw.community.dto;


import lombok.Data;

/**
 * @program: community
 * @description:
 * @author: cone
 * @create: 2020-03-07 16:43
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
