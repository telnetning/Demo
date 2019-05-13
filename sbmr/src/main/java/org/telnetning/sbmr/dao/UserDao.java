package org.telnetning.sbmr.dao;

import org.telnetning.sbmr.entity.User;

public interface UserDao {
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
}
