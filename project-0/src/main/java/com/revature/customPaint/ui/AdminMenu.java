package com.revature.customPaint.ui;

import com.revature.customPaint.models.Store;
import com.revature.customPaint.models.User;
import com.revature.customPaint.services.StoreService;
import com.revature.customPaint.util.annotations.Inject;

import java.util.Scanner;
import java.util.UUID;

public class AdminMenu implements IMenu {
    @Inject
    private final User user;
    private final StoreService storeService;

    @Inject
    public AdminMenu(User user, StoreService storeService) {
        this.user = user;
        this.storeService = storeService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\n" + drawHorizontalLine("| Welcome to admin menu  |", user.getUsername()));
                System.out.println("| Welcome to admin menu " + user.getUsername() + " |");
                System.out.println(drawHorizontalLine("| Welcome to admin menu  |", user.getUsername()));
                System.out.println("[1] Create restaurant");
                System.out.println("[2] Update restaurant");
                System.out.println("[3] Delete restaurant");
                System.out.println("[4] Search restaurant");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        createRestaurant();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

    private void createRestaurant() {
        Scanner scan = new Scanner(System.in);
        Store store = new Store();

        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("| Creating restaurant... |");
                System.out.println("+------------------------+");

                store.setId(UUID.randomUUID().toString());

                System.out.print("Name: ");
                store.setName(scan.nextLine());

                System.out.print("\nCity: ");
                store.setCity(scan.nextLine());

                System.out.print("\nState: ");
                store.setState(scan.nextLine());

                System.out.println("\nPlease confirm restaurant (y/n)");
                System.out.println("\n" + store);

                switch (scan.nextLine()) {
                    case "y":
                        storeService.register(store);
                        break exit;
                    case "n":
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

    private String drawHorizontalLine(String msg, String nameLength) {
        int len = msg.length() + nameLength.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (i == 0 || i == len - 1) sb.append("+");
            else sb.append("-");
        }

        return sb.toString();
    }
}