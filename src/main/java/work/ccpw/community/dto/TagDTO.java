package work.ccpw.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: community
 * @description: 标签对象
 * @author: cone
 * @create: 2020-03-21 16:22
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
