package com.acetex.acetexdasboard.Resource;

import com.acetex.acetexdasboard.Exceptions.EmailExistsException;
import com.acetex.acetexdasboard.Exceptions.ExceptionHandling;
import com.acetex.acetexdasboard.Exceptions.UserNotFoundException;
import com.acetex.acetexdasboard.Exceptions.UsernameExistsException;
import com.acetex.acetexdasboard.Model.User;
import com.acetex.acetexdasboard.Model.UserPrincipal;
import com.acetex.acetexdasboard.Service.UserService;
import com.acetex.acetexdasboard.Utility.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

import java.io.IOException;

import static com.acetex.acetexdasboard.Constants.SecurityConstant.JWT_TOKEN_HEADER;


@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserResource(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal((loginUser));
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader , HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistsException, UsernameExistsException, MessagingException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNonLocked") String isNonLocked,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws UserNotFoundException, EmailExistsException, UsernameExistsException, IOException {
        User newUser = userService.addNewUser(firstName, lastName, username, email, role, Boolean.parseBoolean(isActive) ,Boolean.parseBoolean(isNonLocked), profileImage);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }


    //PRIVATE METHODS
    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }
}
