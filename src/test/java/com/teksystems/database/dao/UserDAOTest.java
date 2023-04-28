package com.teksystems.database.dao;

import org.junit.jupiter.api.*;

import com.teksystems.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findAllUsersTest(){
        userDAO.getAllUsers();
    }

    @Test
    public void findByIdtest(){
        User u = userDAO.findById(7);
        Assertions.assertEquals(u.getFirstName(), "Jonah");
        Assertions.assertEquals(u.getLastName(), "Saywonson");
        Assertions.assertEquals(u.getEmail(), "jonahsaywonson@temple.edu");
    }

    @Test
    @Order(0)
    public void createUserTest(){
        //given
        User given = new User();
        given.setFirstName("Bob");
        given.setLastName("Bobberson");
        given.setEmail("BobbyBoy@Test.com");

        String encryptedPassword = passwordEncoder.encode("12345");
        given.setPassword(encryptedPassword);

        //when
        userDAO.save(given);

        //then
        User actual = userDAO.findByEmailIgnoreCase("BobbyBoy@Test.com");

        Assertions.assertEquals(given.getFirstName(),actual.getFirstName());
        Assertions.assertEquals(given.getLastName(), actual.getLastName());
        Assertions.assertEquals(given.getPassword(), actual.getPassword());
    }

    @Test
    @Order(1)
    public void updateUserTest(){
        //given
        User given = userDAO.findByEmailIgnoreCase("BobbyBoy@Test.com");

        given.setFirstName("Robert");
        given.setLastName("Robertson");

        String encryptedPassword = passwordEncoder.encode("ABCD");
        given.setPassword(encryptedPassword);

        //when
        userDAO.save(given);

        //then
        User actual = userDAO.findByEmailIgnoreCase("BobbyBoy@Test.com");

        Assertions.assertEquals(given.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(given.getLastName(), actual.getLastName());
        Assertions.assertEquals(given.getPassword(), actual.getPassword());
    }

    @Test
    @Order(2)
    public void deleteUserTest(){
        //given
        User given = userDAO.findByEmailIgnoreCase("BobbyBoy@Test.com");

        //when
        userDAO.delete(given);

        //then
        User actual = userDAO.findByEmailIgnoreCase("BobbyBoy@Test.com");
        Assertions.assertNull(actual);
    }
}
