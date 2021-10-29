package com.acetex.acetexdasboard.Service;

import com.acetex.acetexdasboard.Exceptions.EmailExistsException;
import com.acetex.acetexdasboard.Exceptions.EmailNotFoundException;
import com.acetex.acetexdasboard.Exceptions.UserNotFoundException;
import com.acetex.acetexdasboard.Exceptions.UsernameExistsException;
import com.acetex.acetexdasboard.Model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    //User creates new account
    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistsException, UsernameExistsException, MessagingException;
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);

    //
    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistsException, UsernameExistsException, IOException, UserNotFoundException;
    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistsException, UsernameExistsException, IOException, EmailExistsException;
    void deleteUser(Long id);
    void resetPassword(String email) throws MessagingException, EmailNotFoundException, MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, EmailExistsException, UsernameExistsException, IOException, EmailExistsException, UsernameExistsException;

}
