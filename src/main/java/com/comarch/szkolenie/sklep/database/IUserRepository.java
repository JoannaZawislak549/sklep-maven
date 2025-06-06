package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.User;

public interface IUserRepository {
    User findUser(String login);
    void register(String login, String password);
    void persist();
}
