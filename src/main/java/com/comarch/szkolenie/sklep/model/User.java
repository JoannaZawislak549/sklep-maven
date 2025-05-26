package com.comarch.szkolenie.sklep.model;


public class User {
    private String name;
    private String password;
    private Role role;

    public User(String name, String password, Role role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public enum Role {
        USER,
        ADMIN
    }

    public User(){

    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
