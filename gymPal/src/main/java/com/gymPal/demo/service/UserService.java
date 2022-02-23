package com.gymPal.demo.service;

import com.gymPal.demo.entity.Role;
import com.gymPal.demo.model.PasswordModel;
import com.gymPal.demo.model.RegistrationUserModel;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    String registerUser(RegistrationUserModel registrationUserModel, HttpServletRequest request);

    String verifyRegistration(String token);

    String changePasssword(PasswordModel passwordModel, HttpServletRequest request);

    String savePassword(String token, PasswordModel passwordModel);

    String resetPassword(PasswordModel passwordModel, HttpServletRequest request);

    Role saveRole(Role role);

    String addRoleToUser(String username, String rolename);
}
