package com.comarch.szkolenie.sklep.authentication;

public interface IAuthenticator {
    void authenticate(String login, String password);
}
