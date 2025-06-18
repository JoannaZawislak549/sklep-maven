package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepository implements IUserRepository{
    private final String DB_FILE = "database.txt" ;
    private final Map<String, User> users ;


    public UserRepository(){
        this.users = new HashMap<>();
       try(BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))){
           String line;
           while ((line = reader.readLine()) != null && !line.isEmpty()){
               if (line.startsWith("USER")){
                   String[] parts = line.split(";");
                   User user = new User(parts[1], parts[2], User.Role.valueOf(parts[3]));
                   this.users.put(user.getName(), user);
               }
           }
       } catch (IOException e) {
           System.out.println("Plik nie działa!");
       }
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
        persist();
}

@Override
    public void persist(){
        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))){
            String line;
            while ((line = reader.readLine()) != null  && !line.isEmpty()){
                if (!line.startsWith("USER;")){
                    allLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Nie udało się wczytać pliku");
        }

        for(User user : this.users.values()) {
            allLines.add(user.convertToDatabaseLine());
        }


        try (BufferedWriter writer = new BufferedWriter((new FileWriter(DB_FILE)))){
            for(String line : allLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Nie działa zapisywanie!");
        }
    }


}
