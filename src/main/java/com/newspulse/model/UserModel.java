package com.newspulse.model;



import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String fullName;
    private String userEmail;
    private String password;
    private List<String> preferences;

    @Override
    public String toString() {
        return "UserModel{" +
                "fullName='" + fullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
