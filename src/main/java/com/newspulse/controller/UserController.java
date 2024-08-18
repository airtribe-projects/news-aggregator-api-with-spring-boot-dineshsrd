package com.newspulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newspulse.model.UserModel;
import com.newspulse.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
}
