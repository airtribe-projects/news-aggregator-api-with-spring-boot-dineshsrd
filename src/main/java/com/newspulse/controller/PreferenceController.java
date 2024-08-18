package com.newspulse.controller;

import com.newspulse.model.ResponseModel;
import com.newspulse.model.UserModel;
import com.newspulse.repository.UserRepository;
import com.newspulse.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/preference")
public class PreferenceController {

    @Autowired
    private PreferenceService _preferenceService;
    @Autowired private UserRepository _userRepository;

//    @GetMapping(path = "/fetch")
//    public ResponseEntity<ResponseModel> register(@Valid @RequestHeader("Authorization") String token) {
//        try{
//            ResponseModel response = _userService.register(user);
//            _preferenceService.mapUsersToPreferences(response.getData(), user.getPreferences());
//            _newsService.pullNews();
//            return new ResponseEntity<>(response, response.getStatus());
//        } catch (Exception e) {
//            ResponseModel response = new ResponseModel(e.getMessage(), HttpStatus.BAD_REQUEST, null);
//            return new ResponseEntity<>(response, response.getStatus());
//        }
//    }

}
