package com.revature.customPaint.ui;

import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.models.Product;
import com.revature.customPaint.models.Store;
import com.revature.customPaint.models.User;
import com.revature.customPaint.services.InventoryService;
import com.revature.customPaint.services.StoreService;
import com.revature.customPaint.services.ProductService;
import com.revature.customPaint.util.annotations.Inject;
import com.revature.customPaint.util.custom_exceptions.InvalidStoreException;

import java.util.*;

public class AdminMenu implements IMenu {
    @Inject
    private final User user;
    private final StoreService storeService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    @Inject
    public AdminMenu(User user, StoreService storeService, ProductService productService, InventoryService inventoryService) {
        this.user = user;
        this.storeService = storeService;
        this.productService = productService;
        this.inventoryService = inventoryService;
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
                System.out.println("[1] Create store");
                System.out.println("[2] Create product");
                System.out.println("[3] Add product to store");
                System.out.println("[4] Delete store");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        createStore();
                        break;
                    case "2":
                        createProduct();
                        break;
                    case "3":
                        createInventory();
                        break;
                    case "4":
                        deleteStore();
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

    private void createStore() {
        Scanner scan = new Scanner(System.in);
        Store store = new Store();

        store.setId(UUID.randomUUID().toString());

        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("| Building new Store... |");
                System.out.println("+------------------------+");

                System.out.print("Name: ");
                store.setName(scan.nextLine());

                System.out.print("Street: ");
                store.setStreet(scan.nextLine());

                while(true){
                    System.out.print("\nCity: ");
                    String city = scan.nextLine().toLowerCase();

                    try{
                        if(storeService.isNotDuplicateCity(city)){
                            store.setCity(city);
                            break;
                        }
                    }catch (InvalidStoreException e){
                        System.out.println(e.getMessage());
                    }

                }


                System.out.println("\nPlease confirm new store (y/n)");
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

    private void createProduct(){
        Scanner scan = new Scanner(System.in);
        Product product = new Product();


        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("|    Creating Product ...  |");
                System.out.println("+------------------------+");

                product.setId(UUID.randomUUID().toString());

                System.out.print("Name: ");
                product.setName(scan.nextLine());

                System.out.print("\nCategory: ");
                product.setCategory(scan.nextLine());

                System.out.print("\nDescription: ");
                product.setDescription(scan.nextLine());

                System.out.print("\nCost: ");
                product.setCost(scan.nextDouble());
                scan.nextLine();

                System.out.println("\nPlease confirm updates (y/n)");
                System.out.println("\n" + product);

                switch (scan.nextLine()) {
                    case "y":
                        productService.register(product);
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

    public void createInventory(){
        Scanner scan = new Scanner(System.in);
        Inventory inventory = new Inventory();


        exit:
        {
            while (true) {
                System.out.println("\n+-----------------------------+");
                System.out.println("|  Adding product to store ...  |");
                System.out.println("+-------------------------------+");

                //ask admin what product they with to add
                //provide list of products
                //ask admin how many products
                //ask admin what store they wish to add to
                //provide list of stores
                //get store id and add to inventory
                //get product id and add to inventory
                //add quantity to inventory

                List<Product> allProducts;
                int prodInput;

                while(true){
                    System.out.println("What product would you like to add?");
                    allProducts = productService.getAllProducts();

                    for(int i = 0; i < allProducts.size(); i++){
                        System.out.println("[ " + (i + 1) + " ]" + allProducts.get(i).getName());
                    }

                    System.out.println("Enter: ");
                    prodInput = scan.nextInt() - 1;

                    if(prodInput < 0 || prodInput >= allProducts.size()){
                        System.out.println("Invalid input. Try again.");
                    }else{
                        inventory.setProductId(allProducts.get(prodInput).getId());
                        break;
                    }
                }


                System.out.println("How many products would you like to add? ");
                int quantity = scan.nextInt();
                inventory.setQuantity(quantity);

                List<Store> allStores;
                int storeInput;

                while(true){
                    System.out.println("What store would you like to add to?");
                    allStores = storeService.getAllStores();

                    for(int i = 0; i < allStores.size(); i++){
                        System.out.println("[ " + (i + 1) + " ]" + allStores.get(i).getCity());
                    }
                    System.out.println("Enter: ");
                    storeInput = scan.nextInt() - 1;

                    if(storeInput < 0 || storeInput >= allStores.size()){
                        System.out.println("Invalid input. Try again.");
                    }else{
                        inventory.setStoreId(allStores.get(storeInput).getId());
                        scan.nextLine();
                        break;
                    }
                }


                System.out.println("\nPlease confirm updates (y/n)");
                System.out.println("Store: "+  allStores.get(storeInput).getCity());
                System.out.println("Product: "+  allProducts.get(prodInput).getName());
                System.out.println("Quantity: "+  inventory.getQuantity());

                switch (scan.nextLine()) {
                    case "y":
                        inventoryService.register(inventory);
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

    private void deleteStore(){
        Scanner scan = new Scanner(System.in);
        Store store = new Store();

        exit:
        {
            while (true) {
                System.out.println("Which store do you want to delete: ");
                List<Store> allStores = storeService.getAllStores();

                for (int i = 0; i < allStores.size(); i++) {
                    System.out.println("[ " + (i + 1) + " ]" + allStores.get(i).getStreet() + " st., " + allStores.get(i).getCity());
                }
                System.out.println("Enter: ");
                int input = scan.nextInt() - 1;
                scan.nextLine();


                if (input < 0 || input >= allStores.size()) {
                    System.out.println("Invalid input. Try again.");
                } else {
                    System.out.println("\nPlease confirm updates (y/n)");
                    System.out.println("\n" + allStores.get(input).getStreet() + " st., " + allStores.get(input).getCity());

                    switch (scan.nextLine()) {
                        case "y":
                            storeService.delete(allStores.get(input).getId());
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