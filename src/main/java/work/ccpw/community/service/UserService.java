package work.ccpw.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ccpw.community.mapper.UserMapper;
import work.ccpw.community.model.User;

/**
 * @program: community
 * @description: User
 * @author: cone
 * @create: 2020-03-15 17:10
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {

      User dbUser =  userMapper.findByAccountId(user.getAccountId());
      if (dbUser == null){
          // 插入
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else {
          // 更新
          dbUser.setGmtModified(System.currentTimeMillis());
          dbUser.setAvatarUrl(user.getAvatarUrl());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);

      }
    }
}
