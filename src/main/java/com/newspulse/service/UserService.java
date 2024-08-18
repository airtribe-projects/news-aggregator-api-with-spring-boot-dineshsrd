package com.newspulse.service;

import com.newspulse.entity.NewsPulseUser;
import com.newspulse.exception.UserLevelException;
import com.newspulse.model.ResponseModel;
import com.newspulse.model.UserModel;
import com.newspulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public ResponseModel register(UserModel user) throws UserLevelException {
        if (_userRepository.findByUserEmail(user.getUserEmail()) != null) {
            throw new UserLevelException("User already exists");
        }
        NewsPulseUser newsPulseUser = new NewsPulseUser();
        newsPulseUser.setFullName(user.getFullName());
        newsPulseUser.setUserEmail(user.getUserEmail());
        newsPulseUser.setPassword(_passwordEncoder.encode(user.getPassword()));
        _userRepository.save(newsPulseUser);
        return new ResponseModel("User registered successfully", HttpStatus.CREATED, newsPulseUser);
    }

    public NewsPulseUser findByEmail(String email) {
        return _userRepository.findByUserEmail(email);
    }
}
