package work.ccpw.community.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ccpw.community.dto.PaginationDTO;
import work.ccpw.community.dto.QuestionDTO;
import work.ccpw.community.exception.CustomizeErrorCode;
import work.ccpw.community.exception.CustomizeException;
import work.ccpw.community.mapper.QuestionExtMapper;
import work.ccpw.community.mapper.QuestionMapper;
import work.ccpw.community.mapper.UserMapper;
import work.ccpw.community.model.Question;
import work.ccpw.community.model.QuestionExample;
import work.ccpw.community.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: community
 * @description: service
 * @author: cone
 * @create: 2020-03-13 19:42
 **/
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper quesstionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {


        PaginationDTO paginationDTO = new PaginationDTO();
//      Integer totalCount = quesstionMapper.count();
        Integer totalCount = (int) quesstionMapper.countByExample(new QuestionExample());
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        // size*(page-1)
        Integer offset = size * (page - 1);
//        List<Question> questions = quesstionMapper.list(offset, size);

        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = quesstionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question : questions) {
//            User user = userMapper.findById(question.getCreator());
            User user = userMapper.selectByPrimaryKey(question.getCreator());

            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestion(questionDTOList);


        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
//        Integer totalCount = quesstionMapper.countByUserId(userId);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) quesstionMapper.countByExample(questionExample);

        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        // size*(page-1)
        Integer offset = size * (page - 1);
//        List<Question> questions = quesstionMapper.listByUserId(userId, offset, size);
        QuestionExample example = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = quesstionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());

            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestion(questionDTOList);


        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
//        Question question = quesstionMapper.getById(id);
        Question question = quesstionMapper.selectByPrimaryKey(id);

        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTON_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void create(Question question) {
        if (question.getId() == null) {
            // 创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
//             quesstionMapper.create(question);
            quesstionMapper.insertSelective(question);
        } else {
//             question.setGmtModified(System.currentTimeMillis());
//             quesstionMapper.update(question);
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = quesstionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTON_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays
                .stream(tags)
                .filter(StringUtils::isNotBlank)
                .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("|"));
        System.out.println(regexpTag);
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
