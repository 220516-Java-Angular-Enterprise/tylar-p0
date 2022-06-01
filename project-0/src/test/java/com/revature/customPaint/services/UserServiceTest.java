package com.revature.customPaint.services;

import com.revature.customPaint.daos.UserDAO;
import junit.framework.TestCase;

public class UserServiceTest extends TestCase {
    UserService userService = new UserService(new UserDAO());

    public void testIsValidUsername() {
        String validUsername = "tylar_knox03";
        String validUsername2 = "valid_123_username";

        boolean isTrue = userService.isValidUsername(validUsername);
        boolean isTrue2 = userService.isValidUsername(validUsername2);

        assertTrue(isTrue);
        assertTrue(isTrue2);
    }


    public void testIsNotDuplicateUsername() {
        String notDuplicate = "unique_123_username";
        String notDuplicate2 = "this_1_is_not_2_a_duplicate";

        boolean isTrue = userService.isNotDuplicateUsername(notDuplicate);
        boolean isTrue2 = userService.isNotDuplicateUsername(notDuplicate2);

        assertTrue(isTrue);
        assertTrue(isTrue2);
    }

    public void testIsValidPassword() {
        String validPassword = "P@ssw0rd";
        String validPassword2 = "th1s1sv@lid";

        boolean isTrue = userService.isValidPassword(validPassword);
        boolean isTrue2 = userService.isValidPassword(validPassword2);

        assertTrue(isTrue);
        assertTrue(isTrue2);
    }
}