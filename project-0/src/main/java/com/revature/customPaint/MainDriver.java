package com.revature.customPaint;

import com.revature.customPaint.services.UserService;
import com.revature.customPaint.ui.StartMenu;

/* This class purpose is to start our application. */
public class MainDriver {
    public static void main(String[] args) {
        UserService userService = new UserService();

        /* anonymous function. */
        /* This anonymous function will disappear after the start method is done executing. */
        new StartMenu(userService).start();
    }
}
