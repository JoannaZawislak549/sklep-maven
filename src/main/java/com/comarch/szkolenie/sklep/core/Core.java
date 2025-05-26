package com.comarch.szkolenie.sklep.core;

import com.comarch.szkolenie.sklep.authentication.Authenticator;
import com.comarch.szkolenie.sklep.authentication.IAuthenticator;
import com.comarch.szkolenie.sklep.database.IProductRepository;
import com.comarch.szkolenie.sklep.exceptions.FailedAuthenticationException;
import com.comarch.szkolenie.sklep.gui.IGUI;
import com.comarch.szkolenie.sklep.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Core implements ICore {
    private final IProductRepository productRepository;
    private final IAuthenticator authenticator;
    private final IGUI gui;


    @Override
    public void start(){
        boolean run = true;

        while(run) {
            String choice = gui.loginOrRegister();

            switch (choice) {
                case "1":
                    runApp();
                case "2":
                    gui.createLogin();
                    String password = gui.createPassword();
                    String passwordConfirmation = gui.confirmPassword();
                    if (passwordConfirmation.equals(password)) {
                        int logOrEx = gui.confirmCreatingAccount();
                        switch (logOrEx) {
                            case 1:
                                runApp();
                            case 2:
                                run = false;
                                break;
                        }
                    } else {
                        System.out.println("Podane hasła są niezgodne!");
                    }
                case "3":
                    run = false;
            }
        }


    }
    private boolean authenticateUser(){
        boolean result;
        int counter = 0;
        do {
            User user = gui.readCredentials();
            try{
            authenticator.authenticate(user.getName(), user.getPassword());
            result = true;
            } catch (FailedAuthenticationException e){
                result = false;
                gui.incorrectCredentials();
            }
            counter++;
        }
            while(!result && counter < 3);

        return result;
    }

    private void runApp(){
        boolean run = authenticateUser();

        while(run){

            switch (gui.showMenuAndReadChoice()){
                case 1:
                    gui.showProducts();
                    break;
                case 2:
                    if (Authenticator.currentUserRole == User.Role.ADMIN){
                        productRepository.addProductQuantity(gui.chooseProductToAdd(), gui.chooseQuantity());
                    } else {
                        gui.buyProducts();
                    }
                case 3:
                    run = false;
                    break;

            }

        }
    }


}
