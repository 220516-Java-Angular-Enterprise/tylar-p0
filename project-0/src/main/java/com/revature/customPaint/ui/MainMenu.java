package com.revature.customPaint.ui;

import com.revature.customPaint.models.Product;
//import com.revature.customPaint.models.Review;
import com.revature.customPaint.models.User;
import com.revature.customPaint.services.ProductService;
import com.revature.customPaint.services.StoreService;
//import com.revature.customPaint.services.ReviewService;
import com.revature.customPaint.services.UserService;
import com.revature.customPaint.util.annotations.Inject;

import java.util.List;
import java.util.Scanner;

public class MainMenu implements IMenu {
    @Inject
    private final User user;
    private final UserService userService;
    //private final ReviewService reviewService;
    private final StoreService storeService;
    private final ProductService productService;

    @Inject
    public MainMenu(User user, UserService userService, StoreService storeService, ProductService productService) {
        this.user = user;
        this.userService = userService;
        //this.reviewService = reviewService;
        this.storeService = storeService;
        this.productService = productService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\n"+drawHorizontalLine("| Welcome! |", user.getUsername()));
                System.out.println("| Welcome " + user.getUsername() + "!|");
                System.out.println(drawHorizontalLine("| Welcome! |", user.getUsername()));
                System.out.println(drawSquare("| [1] Clothes     |     [2]Cart     |     [3]Profile     |     [x] Sign out |"));
                System.out.println("| [1] Clothes     |     [2]Cart    |      [3]Profile     |     [x] Sign out |");
                System.out.println(drawSquare("| [1] Clothes     |     [2]Cart     |     [3]Profile     |     [x] Sign out |"));

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewClothes();
                        break;
                    case "2":
                        break;
                    case "3":
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

    private void viewClothes() {
        Scanner scan = new Scanner(System.in);
        List<Product> products = productService.getAllProducts();

        System.out.println("\n+---------------------+");
        System.out.println("| Select to add to cart |");
        System.out.println("+-----------------------+");

        while (true){
            for (int i = 0; i < products.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + products.get(i).getName() + " $" + products.get(i).getCost());
            }

            System.out.print("\nEnter: ");
            int input = scan.nextInt() - 1;

            if (input >= 0 && input < products.size()) {
                break;
            }else{
                System.out.println("Invalid product selection.");
            }
        }



        /*while (true) {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("| Please select a restaurant to see reviews |");
            System.out.println("+-------------------------------------------+");

            for (int i = 0; i < stores.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + stores.get(i).getName());
            }

            System.out.print("\nEnter: ");
            int input = scan.nextInt() - 1;

            if (input >= 0 && input < stores.size()) {
                Store selectedStore = stores.get(input);
                List<Review> reviews = reviewService.getReviewByResto(selectedResto.getId());

                System.out.println("\n" + drawHorizontalLine("| Reviews for  |", selectedStore.getName()));
                System.out.println("| Reviews for " + selectedStore.getName() + " |");
                System.out.println(drawHorizontalLine("| Reviews for  |", selectedStore.getName()));

                if (!reviews.isEmpty()) {
                    exit:
                    {
                        while (true) {
                            int newLine = 0;
                            for (Review r : reviews) {
                                User userReview = userService.getUserById(r.getUser_id());
                                System.out.println(r.getMsg() + "\nRating: " + r.getRating() + "\nUser: " + userReview.getUsername());

                                if (newLine < reviews.size() - 1) System.out.println();

                                newLine++;
                            }

                            scan.nextLine();

                            System.out.println("\nDo you want to leave a review? (y/n)");
                            System.out.print("\nEnter: ");

                            switch (scan.nextLine()) {
                                case "y":
                                    break;
                                case "n":
                                    break exit;
                                default:
                                    System.out.println("\nInvalid input!");
                                    break;
                            }
                        }
                    }
                } else {
                    exit:
                    {
                        scan.nextLine();

                        System.out.println("No reviews yet!");
                        System.out.println("\nDo you want to leave a review? (y/n)");
                        System.out.print("\nEnter: ");

                        switch (scan.nextLine()) {
                            case "y":
                                break;
                            case "n":
                                break exit;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }
                    }
                }
            } else {
                System.out.println("\nInvalid restaurant selection.");
            }
        }*/
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

    private String drawSquare(String msg) {
        int len = msg.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append("-");
        }

        return sb.toString();
    }
}