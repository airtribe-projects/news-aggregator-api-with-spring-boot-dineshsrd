package com.newspulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newspulse.entity.NewsPulseUser;
import com.newspulse.model.LoginResponseModel;
import com.newspulse.model.ResponseModel;
import com.newspulse.model.UserModel;
import com.newspulse.service.AuthService;
import com.newspulse.service.JwtService;
import com.newspulse.service.NewsService;
import com.newspulse.service.PreferenceService;
import com.newspulse.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService _userService;

    @Autowired
    private NewsService _newsService;

    @Autowired
    private PreferenceService _preferenceService;

    @Autowired
    private JwtService _jwtService;

    @Autowired
    private AuthService _authenticationService;

    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this._jwtService = jwtService;
        this._authenticationService = authenticationService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseModel> register(@Valid @RequestBody UserModel user) {
        try {
            ResponseModel response = _userService.register(user);
            _preferenceService.mapUsersToPreferences(response.getData(), user.getPreferences());
            //_newsService.pullNews();
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseModel response = new ResponseModel(e.getMessage(), HttpStatus.BAD_REQUEST, null);
            return new ResponseEntity<>(response, response.getStatus());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> authenticate(@RequestBody UserModel loginUserDto) {
        ResponseModel response = new ResponseModel();
        try{
            NewsPulseUser authenticatedUser = _authenticationService.authenticate(loginUserDto);
            System.out.println(authenticatedUser);
            String jwtToken = _jwtService.generateToken(authenticatedUser);
            LoginResponseModel loginResponse = new LoginResponseModel();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(_jwtService.getExpirationTime());
            response.setMessage("Login successful!");
            response.setStatus(HttpStatus.OK);
            response.setData(loginResponse);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception ex){
            ex.printStackTrace();
            response.setMessage(ex.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED);
            response.setData(null);
            return new ResponseEntity<>(response, response.getStatus());
        }

    }
}
