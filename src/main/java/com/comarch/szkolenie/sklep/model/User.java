package com.comarch.szkolenie.sklep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String password;
    private Role role;


    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public enum Role {
        USER,
        ADMIN
    }


    public String convertToDatabaseLine(){
        return String.join(";",  this.name, this.password,
                this.role+"");
    }

}
