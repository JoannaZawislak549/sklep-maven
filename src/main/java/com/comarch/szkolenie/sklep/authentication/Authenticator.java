package com.comarch.szkolenie.sklep.authentication;

import com.comarch.szkolenie.sklep.database.IUserRepository;
import com.comarch.szkolenie.sklep.exceptions.FailedAuthenticationException;
import com.comarch.szkolenie.sklep.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Authenticator implements IAuthenticator{
    private final IUserRepository userRepository;
    private final String seed = "dwzl{MVT+-b9%aYLAnbeRz!&=KCePCJk5K8cpo5!Mzl9dnC%z'";
    public static User.Role currentUserRole;


    @Override
    public void authenticate(String login, String password){
        User user = this.userRepository.findUser(login);


        if(user == null || !user.getPassword().equals(DigestUtils.md5Hex(password + seed))) {
            throw new FailedAuthenticationException();
        }
        currentUserRole = user.getRole();
    }

}
