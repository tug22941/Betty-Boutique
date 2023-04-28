package com.teksystems.database.dao;

import com.teksystems.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO  extends JpaRepository<User, Long> {

    //get all users - JPA hibernate
    @Query("From User u")
    List<User> getAllUsers();

    User findByEmailIgnoreCase(String email);

    //get user by id - JPA hibernate
    @Query("From User u where u.id = :id")
    User findById(Integer id);

    boolean existsByEmail(String email);

}
