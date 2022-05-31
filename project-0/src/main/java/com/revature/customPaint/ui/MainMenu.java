package com.revature.customPaint.ui;

import com.revature.customPaint.models.*;
//import com.revature.customPaint.models.Review;
import com.revature.customPaint.services.*;
//import com.revature.customPaint.services.ReviewService;
import com.revature.customPaint.util.annotations.Inject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu implements IMenu {
    @Inject
    private final User user;
    private Cart cart;
    private History history;
    private final UserService userService;
    //private final ReviewService reviewService;
    private final StoreService storeService;
    private final ProductService productService;
    private final CartService cartService;
    private final InventoryService inventoryService;
    private final HistoryService historyService;

    @Inject
    public MainMenu(User user, Cart cart, History history, UserService userService, StoreService storeService, ProductService productService, CartService cartService, InventoryService inventoryService, HistoryService historyService) {
        this.user = user;
        this.cart = cart;
        this.history = history;
        this.userService = userService;
        this.storeService = storeService;
        this.productService = productService;
        this.cartService = cartService;
        this.inventoryService = inventoryService;
        this.historyService = historyService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        List<Store> stores = storeService.getAllStores();
        String storeId;
        exit:
        {
            while (true) {
                System.out.println("\n"+drawHorizontalLine("| Welcome! |", user.getUsername()));
                System.out.println("| Welcome " + user.getUsername() + "!|");
                System.out.println(drawHorizontalLine("| Welcome! |", user.getUsername()));

                System.out.println("\n+---------------------------------------+");
                System.out.println("| Which store would you like to shop at?  |");
                System.out.println("+-----------------------------------------+");

                while(true){
                    for (int i = 0; i < stores.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + stores.get(i).getCity());
                    }

                    System.out.println("Enter: ");
                    int storeInput = scan.nextInt() - 1;
                    scan.nextLine();

                    if(storeInput < 0 || storeInput >= stores.size()){
                        System.out.println("Invalid store selection.");
                    }else{
                        storeId = stores.get(storeInput).getId();
                        break;
                    }
                }




                System.out.println(drawSquare("| [1] Clothes     |     [2]Cart     |     [3]Profile     |     [x] Sign out |"));
                System.out.println("| [1] Clothes     |     [2]Cart    |      [3]Profile     |     [x] Sign out |");
                System.out.println(drawSquare("| [1] Clothes     |     [2]Cart     |     [3]Profile     |     [x] Sign out |"));

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewClothes(storeId);
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

    private void viewClothes(String storeId) {
        Scanner scan = new Scanner(System.in);
        List<Product> products = productService.getAllProducts();
        List<Cart> cartList = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.getAllInventory();

        String itemId;
        String prodId;
        String prodName;
        double prodPrice;
        int prodQuantity;
        int count = 0;
        int count2 = 0;
        int inventoryQuantity = 0;

        System.out.println("\n+---------------------+");
        System.out.println("| Select to add to cart |");
        System.out.println("+-----------------------+");

        //displays all products
        exit:
        {
            while (true){
                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName() + " $" + products.get(i).getCost());
                }

                System.out.print("\nEnter: ");
                int input = scan.nextInt() - 1;

                prodId = products.get(input).getId();
                prodName = products.get(input).getName();
                prodPrice = products.get(input).getCost();

                //loop through inventories
                //if the inventory store id matches store id chosen
                //and product id matches product chosen
                //get and store quantity

                for(Inventory i : inventoryList){
                    if(i.getStoreId().equals(storeId) && i.getProductId().equals(prodId)){
                        inventoryQuantity = i.getQuantity();
                    }
                }
                System.out.println("Inventory quantity: " + inventoryQuantity);

                if (input >= 0 && input < products.size()) {
                    itemId = UUID.randomUUID().toString();

                    while (true){
                        System.out.println("How many? ");
                        prodQuantity = scan.nextInt();
                        scan.nextLine();

                        if(prodQuantity > inventoryQuantity){
                            System.out.println("Sorry there are only " + inventoryQuantity);
                        }else{
                            break;
                        }
                    }

                    //adds items to cart list
                    cart = new Cart(itemId, prodId, prodName, prodPrice, prodQuantity);
                    cartList.add(cart);

                    System.out.println("Checkout (y/n)");
                    if(Objects.equals(scan.nextLine(), "y")){
                        //loop through list of carts and call toString on each index to display chosen items
                        //registers each item
                        double sum = 0;
                        for (Cart item : cartList) {
                            System.out.println(item.partialToString() + "\n");
                            sum += item.getProductPrice() * item.getProductQuantity();
                        }

                        System.out.println("Total: $" + sum);

                        System.out.println("Buy (y/n): ");

                        switch (scan.nextLine()){
                            case "y":
                                //add order to history
                                Date date = Calendar.getInstance().getTime();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String strDate = dateFormat.format(date);
                                
                                history = new History(UUID.randomUUID().toString(), user.getId(), strDate, sum);
                                historyService.register(history);
                                break exit;
                            case "n":
                                break;
                            default:
                                System.out.println("\nInvalid input.");
                                break;
                        }
                        break;
                    }
                }else{
                    System.out.println("Invalid product selection.");
                    break;
                }

            /*if(input == 0){
                count++;
                if(count > 1){
                    System.out.println("You've already added " + prodName + " to your cart.");
                    count--;
                    break;
                }
            }else if(input == 1){
                count2++;
                if(count2 > 1){
                    System.out.println("You've already added " + prodName + " to your cart.");
                }
            }*/


            }
        }


        //add items to cart


        //add to order history


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