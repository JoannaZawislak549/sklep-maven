package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.Product;
import com.comarch.szkolenie.sklep.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository implements IUserRepository{
    private final String DB_FILE = "users.txt" ;
    private final Map<String, User> users = new HashMap<>();


    private UserRepository(){
        this.users.put("admin", new User("admin", "0c0dc91116509eff21f024618df6b0ee", User.Role.ADMIN));
        this.users.put("janusz", new User("janusz", "f9d161d428171ca369124d6491c383ef", User.Role.USER));

    }

@Override
public User findUser(String login){
        return this.users.get(login);

}
@Override
public void register(String login, String password){
        String toHashPassword = password+"dwzl{MVT+-b9%aYLAnbeRz!&=KCePCJk5K8cpo5!Mzl9dnC%z'";
        String hash = DigestUtils.md5Hex(toHashPassword);
        this.users.put(login, new User(login, hash, User.Role.USER));
}

    public void persist(){
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(DB_FILE)))){
            for(User user : this.users.values()) {
                writer.write(user.convertToDatabaseLine());
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Nie działa zapisywanie!");
        }
    }


}
