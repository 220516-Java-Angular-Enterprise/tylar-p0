package com.revature.customPaint.ui;

import com.revature.customPaint.models.User;
import com.revature.customPaint.util.annotations.Inject;

public class MainMenu implements IMenu {
    @Inject
    private final User user;

    @Inject
    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome to Custom Paint " + user.getUsername());
    }
}