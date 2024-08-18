package com.newspulse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newspulse.entity.NewsPulseUser;
import com.newspulse.exception.UserLevelException;
import com.newspulse.model.UserModel;
import com.newspulse.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public NewsPulseUser authenticate(UserModel input) throws Exception {
        NewsPulseUser user = (NewsPulseUser) _userRepository.findByUserEmail(input.getUserEmail());
        if(user!=null){
            if (_passwordEncoder.matches(input.getPassword(), user.getPassword())){
                return user;
            }else {
                throw new UserLevelException("Invalid username/password");
            }
        }
        throw new UserLevelException("User not found");
    }
}
