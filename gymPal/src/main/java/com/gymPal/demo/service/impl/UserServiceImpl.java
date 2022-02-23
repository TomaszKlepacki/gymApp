package com.gymPal.demo.service.impl;

import com.gymPal.demo.entity.Role;
import com.gymPal.demo.entity.Token;
import com.gymPal.demo.entity.User;
import com.gymPal.demo.enums.TokenType;
import com.gymPal.demo.enums.TokenValid;
import com.gymPal.demo.model.PasswordModel;
import com.gymPal.demo.model.RegistrationUserModel;
import com.gymPal.demo.repository.RoleRepository;
import com.gymPal.demo.repository.TokenRepository;
import com.gymPal.demo.repository.UserRepository;
import com.gymPal.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    TokenRepository tokenRepository;
    PasswordEncoder passwordEncoder;
    JavaMailSenderImpl javaMailSender;

    private static final String EMAIL_TAKEN = "EMAIL_TAKEN";
    private static final String EMAIL_NOT_FOUND = "EMAIL_NOT_FOUND";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String USER_REGISTERED = "USER_REGISTERED";
    private static final String USER_VERIFIED = "USER_VERIFIED";
    private static final String USER_VERIFIED_ALREADY = "USER_VERIFIED_ALREADY";
    private static final String PASSWORD_CHANGED = "PASSWORD_CHANGED";
    private static final String PASSWORD_OLD_INCORRECT = "PASSWORD_OLD_INCORRECT";

    private static final String TOKEN_USED = "TOKEN_USED";
    private static final String TOKEN_SENT = "TOKEN_SENT";
    private static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    private static final String TOKEN_INVALID = "TOKEN_INVALID";
    private static final String TOKEN_NOT_FOUND = "TOKEN_NOT_FOUND";


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, JavaMailSenderImpl javaMailSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Transactional
    public String registerUser(RegistrationUserModel registrationUserModel, HttpServletRequest request) {
        User user = userRepository.findByEmail(registrationUserModel.getEmail());
        String url;
        if (user != null) {
            return EMAIL_TAKEN;
        } else {
            user = generateUser(registrationUserModel);
            String token = UUID.randomUUID().toString();
            Token registrationToken = new Token(user, token, TokenType.NEW_ACCOUNT_VERIFICATION, TokenValid.TOKEN_VALID);
            url = generateVerificationTokenUrl(generateUrl(request), registrationToken);
            userRepository.save(user);
            tokenRepository.save(registrationToken);
            // TODO sendEmailWithVerificationToken(user, url);
            log.info("Url: {}", url);
            log.info("Token: {}", token);
        }
        return USER_REGISTERED;
    }

    @Override
    @Transactional
    public String verifyRegistration(String token) {
        Token registrationToken = tokenRepository.findByToken(token);
        if (registrationToken == null) {
            return TOKEN_INVALID;
        } else {
            if (registrationToken.getExpirationDate().getTime() < new Date().getTime()) {
                return TOKEN_EXPIRED;
            } else if (registrationToken.getTokenValid().equals(TokenValid.TOKEN_USED)) {
                return TOKEN_USED;
            } else {
                if (!registrationToken.getTokenType().equals(TokenType.NEW_ACCOUNT_VERIFICATION)) {
                    return TOKEN_INVALID;
                }

                User user = registrationToken.getUser();
                if (user.isEnabled()) {
                    return USER_VERIFIED_ALREADY;
                } else {
                    registrationToken.setTokenValid(TokenValid.TOKEN_USED);
                    user.setEnabled(true);
                    userRepository.save(user);
                    tokenRepository.save(registrationToken);
                }
            }
        }
        return USER_VERIFIED;
    }

    @Override
    public String changePasssword(PasswordModel passwordModel, HttpServletRequest request) {
        User user = userRepository.findByEmail(passwordModel.getEmail());
        String url;
        if (user == null) {
            return EMAIL_NOT_FOUND;
        } else {
            if (!checkIfOldPasswordIsValid(passwordModel.getPassword(), user)) {
                return PASSWORD_OLD_INCORRECT;
            } else {
                user.setPassword(passwordModel.getNewPassword());
                userRepository.save(user);
            }
        }
        return PASSWORD_CHANGED;
    }

    @Override
    public String resetPassword(PasswordModel passwordModel, HttpServletRequest request) {
        User user = userRepository.findByEmail(passwordModel.getEmail());
        String url;
        if (user == null) {
            return USER_NOT_FOUND;
        } else {
            String token = UUID.randomUUID().toString();
            Token resetPasswordToken = new Token(user, token, TokenType.FORGOT_PASSWORD, TokenValid.TOKEN_VALID);
            url = generateChangePasswordTokenUrl(generateUrl(request), resetPasswordToken);
            tokenRepository.save(resetPasswordToken);
            //TODO sendEmailWithResetPasswordToken(user,url);
            log.info("Url: {}", url);
            log.info("Token: {}", token);
        }
        return TOKEN_SENT;
    }

    @Override
    public String savePassword(String token, PasswordModel passwordModel) {
        Token changePasswordToken = tokenRepository.findByToken(token);
        if (changePasswordToken == null) {
            return TOKEN_NOT_FOUND;
        } else {
            if (changePasswordToken.getExpirationDate().getTime() < new Date().getTime()) {
                return TOKEN_EXPIRED;
            } else if (!changePasswordToken.getTokenType().equals(TokenType.FORGOT_PASSWORD)) {
                return TOKEN_INVALID;
            } else if (changePasswordToken.getTokenValid().equals(TokenValid.TOKEN_USED)) {
                return TOKEN_USED;
            } else {
                User user = userRepository.findByEmail(changePasswordToken.getUser().getEmail());
                changePasswordToken.setTokenValid(TokenValid.TOKEN_USED);
                user.setPassword(passwordEncoder.encode(passwordModel.getNewPassword()));
                userRepository.save(user);
                tokenRepository.save(changePasswordToken);
            }
        }
        return PASSWORD_CHANGED;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public String addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename.toUpperCase());
        user.getRoles().add(role);
        userRepository.save(user);
        return "ADDED";//TODO
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if (user==null){
            return null;//TODO
        } else {
            Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
            user.getRoles().forEach(role->{
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        }
    }

    public Role addRole(String rolename) {
        return roleRepository.findByName(rolename);
    }

    private void sendEmailWithVerificationToken(User user, String url) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(user.getEmail());
        message.setSubject("Registration");
        message.setText(url);
        message.setReplyTo(user.getEmail());
        javaMailSender.send(message);
    }

    private void sendEmailWithResetPasswordToken(User user, String url) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("football@score.com");
        message.setTo(user.getEmail());
        message.setSubject("Reset password");
        message.setText(url);
        message.setReplyTo(user.getEmail());
        javaMailSender.send(message);
    }


    private User generateUser(RegistrationUserModel registrationUserModel) {
        User user = new User();
        user.setUsername(registrationUserModel.getUsername());
        user.setEmail(registrationUserModel.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserModel.getPassword()));
        user.setRoles(new ArrayList<>());
        user.getRoles().add(addRole("ROLE_USER"));
        return user;
    }

    private String generateUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    private String generateVerificationTokenUrl(String applicationUrl, Token token) {
        return applicationUrl +
                "/verifyRegistration?token=" +
                token.getToken();
    }

    private String generateChangePasswordTokenUrl(String generateUrl, Token token) {
        return generateUrl
                + "/savePassword?token="
                + token.getToken();
    }

    private boolean checkIfOldPasswordIsValid(String oldPassword, User user) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}