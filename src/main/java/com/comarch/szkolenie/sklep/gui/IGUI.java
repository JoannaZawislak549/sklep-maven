package com.comarch.szkolenie.sklep.gui;

import com.comarch.szkolenie.sklep.model.User;

public interface IGUI {
    String showMenuAndReadChoice();
    void showProducts();
    void buyProducts();
    int chooseProduct();
    int chooseQuantity();
    int chooseProductToAdd();
    User readCredentials();
    void incorrectCredentials();
    String loginOrRegister();
    String createLogin();
    String createPassword();
    String confirmPassword();
    String confirmCreatingAccount(String login, String password);
}
