package work.ccpw.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ccpw.community.dto.QuestionDTO;
import work.ccpw.community.mapper.QuesstionMapper;
import work.ccpw.community.mapper.UserMapper;
import work.ccpw.community.model.Question;
import work.ccpw.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community
 * @description: service
 * @author: cone
 * @create: 2020-03-13 19:42
 **/
@Service
public class QuestionService {
    @Autowired
    private QuesstionMapper quesstionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = quesstionMapper.list();

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question :questions){
            User user = userMapper.findById(question.getCreator());
            System.out.println(user);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
}
