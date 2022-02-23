package com.gymPal.demo.controller;

import com.gymPal.demo.model.PasswordModel;
import com.gymPal.demo.model.RegistrationUserModel;
import com.gymPal.demo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

    private final UserServiceImpl userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationUserModel registrationUserModel, final HttpServletRequest request) {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(userService.registerUser(registrationUserModel, request));
    }

    @PostMapping("/verifyRegistration")
    public ResponseEntity<?> verifyRegistration(@RequestParam("token") String token) {
        return ResponseEntity.ok().body(userService.verifyRegistration(token));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordModel passwordModel, final HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.changePasssword(passwordModel, request));
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, final HttpServletRequest request) {
        return userService.resetPassword(passwordModel, request);
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
        return userService.savePassword(token, passwordModel);
    }

    @PostMapping("/admin/addRole")
    public ResponseEntity<?>addRole(@RequestParam("username")String username, @RequestParam("role")String role){
        return ResponseEntity.ok().body(userService.addRoleToUser(username,role));
    }

    @GetMapping("/admin/hello")
    public String yea(){
        return "Hello admin";
    }

}
