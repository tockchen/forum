package work.ccpw.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import work.ccpw.community.model.Question;

import java.util.List;

/**
 * @program: community
 * @description: quesstion数据库操作
 * @author: cone
 * @create: 2020-03-13 12:48
 **/
@Mapper
public interface QuesstionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question")
    List<Question> list();
}
