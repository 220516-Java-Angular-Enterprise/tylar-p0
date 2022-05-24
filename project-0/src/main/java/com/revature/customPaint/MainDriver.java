package com.revature.customPaint;

import com.revature.customPaint.daos.UserDAO;
import com.revature.customPaint.services.UserService;
import com.revature.customPaint.ui.StartMenu;
import com.revature.customPaint.util.database.DatabaseConnection;

/* This class purpose is to start our application. */
public class MainDriver {
    public static void main(String[] args) {
        /* anonymous function. */
        /* This anonymous function will disappear after the start method is done executing. */
        new StartMenu(new UserService(new UserDAO())).start();
    }
}
