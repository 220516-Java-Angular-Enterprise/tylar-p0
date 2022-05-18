package com.revature.customPaint.ui;

import com.revature.customPaint.models.User;

public class MainMenu implements IMenu {
    //we don't want the user to change
    private final User user;

    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome to the main menu " + user.getUsername());
    }
}