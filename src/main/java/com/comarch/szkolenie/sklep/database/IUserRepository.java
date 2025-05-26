package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.User;

public interface IUserRepository {
    public User findUser(String login);
    public void register(String login, String password);
}
