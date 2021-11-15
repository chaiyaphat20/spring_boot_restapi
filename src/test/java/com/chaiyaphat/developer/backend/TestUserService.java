package com.chaiyaphat.developer.backend;

import com.chaiyaphat.developer.backend.entity.User;
import com.chaiyaphat.developer.backend.exception.BaseException;
import com.chaiyaphat.developer.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.create(
                TestCreateData.email,
                TestCreateData.password,
                TestCreateData.name
        );
        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        //check equal
        Assertions.assertEquals(TestCreateData.name, user.getName());
        Assertions.assertEquals(TestCreateData.email, user.getEmail());

        //check password
        boolean isMatch = userService.matchPassword(TestCreateData.password, user.getPassword());
        Assertions.assertTrue(isMatch);

    }


    @Order(2)
    @Test
    void TestUpdate() throws BaseException {
        //test update
        //find
        Optional<User> otp = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(otp.isPresent());  //otp is not emtyp
        User user = otp.get();
        User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

        //check update
        Assertions.assertNotNull(updatedUser);  //ควร check null ก่อน
        Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());  //ถ้า update จริงต้องเท่ากัน
    }

    @Order(3)
    @Test
    void TestDelete() {
        //test delete
        //find
        Optional<User> otp = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(otp.isPresent());  //otp is not emtyp  //ถ้ามีค่าจะได้ true
        User user = otp.get();
        userService.deleteById(user.getId());

        //found and delete
        //ถ้าสั่งลบไปแล้วต้องหาไม่เจอ
        Optional<User> otpDelete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(otpDelete.isEmpty()); //check ว่าจริงมั้ย โดย สมมุติว่า ค่า opt ต้องได้ Empty

    }

    interface TestCreateData {
        String email = "art@test.com";
        String password = "cocoless";
        String name = "chaiyaphat";
    }

    interface TestUpdateData {
        String name = "butsaba";
    }
}
