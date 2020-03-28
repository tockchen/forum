package work.ccpw.community.dto;

import lombok.Data;

/**
 * @program: community
 * @description:
 * @author: cone
 * @create: 2020-03-26 13:14
 **/
@Data
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
