package work.ccpw.forum.dto;

import lombok.Data;

/**
 * @program: community
 * @description: 模型
 * @author: cone
 * @create: 2020-03-22 14:23
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
