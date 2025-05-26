package com.comarch.szkolenie.sklep.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;


@Configuration
@ComponentScan(basePackages = {
        "com.comarch.szkolenie.sklep.authentication",
        "com.comarch.szkolenie.sklep.core",
        "com.comarch.szkolenie.sklep.database",
        "com.comarch.szkolenie.sklep.gui"
})



public class AppConfiguration {
    @Bean
    public Scanner scanner() {return new Scanner(System.in);}

    }


