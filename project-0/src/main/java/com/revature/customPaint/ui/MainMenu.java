package com.revature.customPaint.ui;

import com.revature.customPaint.models.*;
//import com.revature.customPaint.models.Review;
import com.revature.customPaint.services.*;
//import com.revature.customPaint.services.ReviewService;
import com.revature.customPaint.util.annotations.Inject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        String storeId = null;
        int storeInput = 0;

        exit:
        {
            while (true) {
                System.out.println("\n"+drawHorizontalLine("| Welcome! |", user.getUsername()));
                System.out.println("| Welcome " + user.getUsername() + "!|");
                System.out.println(drawHorizontalLine("| Welcome! |", user.getUsername()));

                System.out.println("\n+---------------------------------------+");
                System.out.println("| Which store would you like to shop at?  |");
                System.out.println("+-----------------------------------------+");

                    do {
                        for (int i = 0; i < stores.size(); i++) {
                            System.out.println("[" + (i + 1) + "] " + stores.get(i).getCity());
                        }

                        System.out.println("Enter: ");

                        while (!scan.hasNextInt()) {
                            String input = scan.next();
                            System.out.printf("\"%s\" is not a valid number.%n", input);
                            System.out.println("Enter: ");
                        }
                        storeInput = scan.nextInt() - 1;
                        scan.nextLine();
                    } while (storeInput < 0);

                while(true){
                    if(storeInput < 0 || storeInput >= stores.size()){
                        System.out.println("Invalid store selection.");
                    }else{
                        storeId = stores.get(storeInput).getId();
                        break;
                    }

                    for (int i = 0; i < stores.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + stores.get(i).getCity());
                    }

                    System.out.println("Enter: ");
                    storeInput = scan.nextInt() - 1;
                    scan.nextLine();


                }

                System.out.println(drawSquare("| [1] Clothes     |     [2]Order History     |     [3]Profile     |     [x] Sign out |"));
                System.out.println("| [1] Clothes     |     [2]Order History    |      [3]Profile     |     [x] Sign out |");
                System.out.println(drawSquare("| [1] Clothes     |     [2]Order History     |     [3]Profile     |     [x] Sign out |"));

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewClothes(storeId);
                        break;
                    case "2":
                        viewOrderHistory(user.getId());
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
        String prodId = null;
        String prodName = null;
        double prodPrice = 0;
        int prodQuantity;
        int count = 0;
        int count2 = 0;
        int inventoryQuantity = 0;
        int input = 0;
        boolean tf = true;
        int currentInventory;

        System.out.println("\n+---------------------+");
        System.out.println("| Select to add to cart |");
        System.out.println("+-----------------------+");

        //displays all products
        exit:
        {
            while (true){
                prodId = products.get(input).getId();
                prodName = products.get(input).getName();
                prodPrice = products.get(input).getCost();

                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName() + " $" + products.get(i).getCost());
                }

                System.out.print("\nEnter: ");
                input = scan.nextInt() - 1;


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
                            currentInventory = inventoryQuantity - prodQuantity;
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

                                //subtract quantity with inventory quantity matching product number
                                System.out.println(currentInventory);
                                inventoryService.update(storeId, prodId, currentInventory);
                                break exit;
                            case "n":
                                break;
                            default:
                                System.out.println("\nInvalid input.");
                                break;
                        }
                    }
                }else{
                    System.out.println("Invalid product selection.");
                    break;
                }
            }
        }

    }

    private void viewOrderHistory(String userId){
        //filter through list of order histories to find orders where history user id equals user id
        //adds to list
        List<History> allHistory = historyService.getAllHistories();
        Scanner scan = new Scanner(System.in);

        System.out.println("\n+-------------+");
        System.out.println("| Order History |");
        System.out.println("+---------------+");

        List<History> userHistory = allHistory.stream().filter(h -> h.getUserId().equals(userId)).collect(Collectors.toList());
        userHistory.forEach((h -> System.out.println(h.toString() + " " + "\n")));

        System.out.println("\n         +------------------------+");
        System.out.println("Sort by  | earliest to oldest (1) |");
        System.out.println("         +------------------------+");
        System.out.println("\n         +------------------------+");
        System.out.println("         | oldest to earliest (2) |");
        System.out.println("         +------------------------+");

        switch (scan.nextLine()){
            case "1":
                List<History> sortByDate = allHistory.stream().sorted(Comparator.comparing(History::getOrderDate)).collect(Collectors.toList());
                sortByDate.forEach(d -> System.out.println(d + " " + "\n"));
                break;
            case "2":
                List<History> sortByDateReverse = allHistory.stream().sorted(Comparator.comparing(History::getOrderDate).reversed()).collect(Collectors.toList());
                sortByDateReverse.forEach(d -> System.out.println(d + " " + "\n"));
                break;
            default:
                System.out.println("Invalid input");
                break;
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

    private String drawSquare(String msg) {
        int len = msg.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append("-");
        }

        return sb.toString();
    }
}