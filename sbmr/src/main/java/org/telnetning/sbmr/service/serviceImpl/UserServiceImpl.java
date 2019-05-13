package org.telnetning.sbmr.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.telnetning.sbmr.dao.UserDao;
import org.telnetning.sbmr.entity.User;
import org.telnetning.sbmr.service.UserService;

import javax.annotation.Resource;

@Service ("userService")
public class UserServiceImpl implements UserService
{
    @Resource
    private UserDao userDao;
    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User record) {
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }
}


