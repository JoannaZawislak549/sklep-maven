package com.comarch.szkolenie.sklep;

import com.comarch.szkolenie.sklep.configuration.AppConfiguration;
import com.comarch.szkolenie.sklep.core.ICore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {


    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        ICore core = context.getBean(ICore.class);
        core.start();
    }
}
