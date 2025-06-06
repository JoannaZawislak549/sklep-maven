package com.comarch.szkolenie.sklep.gui;

import com.comarch.szkolenie.sklep.authentication.Authenticator;
import com.comarch.szkolenie.sklep.database.IProductRepository;
import com.comarch.szkolenie.sklep.database.IUserRepository;
import com.comarch.szkolenie.sklep.model.Product;
import com.comarch.szkolenie.sklep.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class GUI implements IGUI {
    private final Scanner scanner;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;


    @Override
    public String showMenuAndReadChoice(){
        System.out.println("Witaj w osiedlowym sklepiku internetowym. Co chcesz "+
                "zrobić?");
        System.out.println("1. Przejrzeć listę produktów");
        System.out.println(Authenticator.currentUserRole == User.Role.ADMIN ? "2. Dodać produkt" : "2. Kupić produkt");
        System.out.println("3. Wyjść");

        return scanner.nextLine();
    }

    @Override
    public void showProducts(){
        for(Product product: this.productRepository.getProducts()){
            System.out.println(product);
        }
    }

    @Override
    public void buyProducts(){
        int chosenId = chooseProduct();
        int chosenQuant = chooseQuantity();
        boolean canBeSold = this.productRepository.checkIfAvailable(chosenId, chosenQuant);
        if (canBeSold){
            System.out.println("Twoja należność to: " +
                    this.productRepository.prizeCalculator(chosenId, chosenQuant));

        }else{
            System.out.println("Przepraszamy, nie ma na stanie tyle towaru!");
        }


    }

    @Override
    public int chooseProduct(){
        System.out.println("Wpisz id produktu który chcesz kupić: ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public int chooseQuantity(){
        System.out.println("Wpisz liczbę sztuk produktu: ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public int chooseProductToAdd(){
        System.out.println("Wpisz id produktu którego chcesz zwiększyć ilość: ");
        return Integer.parseInt(scanner.nextLine());
    }


    @Override
    public User readCredentials(){
        System.out.println("Podaj login: ");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło: ");
        String password = scanner.nextLine();
        return new User(login, password);
    }

    @Override
    public void incorrectCredentials(){
        System.out.println("Incorrect Credentials!");
    }

    @Override
    public String loginOrRegister(){
        System.out.println("1. Logowanie");
        System.out.println("2. Rejestracja");
        System.out.println("3. Wyjście");
        return scanner.nextLine();
    }

    @Override
    public String createLogin(){
        System.out.println("Podaj nazwę użytkownika");
        return scanner.nextLine();

    }

    @Override
    public String createPassword(){
        System.out.println("Podaj hasło");
        return scanner.nextLine();
    }

    @Override
    public String confirmPassword(){
        System.out.println("Potwierdź hasło");
        return scanner.nextLine();
    }

    @Override
    public String confirmCreatingAccount(String login, String password){
        userRepository.register(login, password);
        System.out.println("Gratulacje! Konto zostało założone. " +
                "Czy chesz się teraz ");
        System.out.println("1. Zalogować");
        System.out.println("2. Wyjść");
        return scanner.nextLine();
    }



}
