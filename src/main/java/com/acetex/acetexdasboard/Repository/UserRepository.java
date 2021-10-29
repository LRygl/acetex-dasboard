package com.acetex.acetexdasboard.Repository;

import com.acetex.acetexdasboard.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);

}
