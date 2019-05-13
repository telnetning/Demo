package org.telnetning.sbmr.service;

import org.telnetning.sbmr.entity.User;

public interface UserService
{
    public User getUserById(int userId);

    boolean addUser(User record);
}
