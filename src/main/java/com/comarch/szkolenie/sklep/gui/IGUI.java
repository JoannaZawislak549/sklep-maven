package com.comarch.szkolenie.sklep.gui;

import com.comarch.szkolenie.sklep.model.User;

public interface IGUI {
    public int showMenuAndReadChoice();
    public void showProducts();
    public void buyProducts();
    public int chooseProduct();
    public int chooseQuantity();
    public int chooseProductToAdd();
    public User readCredentials();
    public void incorrectCredentials();
    public String loginOrRegister();
    public String createLogin();
    public String createPassword();
    public String confirmPassword();
    public int confirmCreatingAccount();
}
