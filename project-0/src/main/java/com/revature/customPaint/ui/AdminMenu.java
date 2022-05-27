package com.revature.customPaint.ui;

import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.models.Product;
import com.revature.customPaint.models.Store;
import com.revature.customPaint.models.User;
import com.revature.customPaint.services.InventoryService;
import com.revature.customPaint.services.StoreService;
import com.revature.customPaint.services.ProductService;
import com.revature.customPaint.util.annotations.Inject;

import java.util.Scanner;
import java.util.UUID;
import java.util.Random;

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
                System.out.println("[2] Update store");
                System.out.println("[3] Delete store");
                System.out.println("[4] Search store");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        createStore();
                        break;
                    case "2":
                        updateStore();
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

    private void createStore() {
        Scanner scan = new Scanner(System.in);
        Store store = new Store();
        Random rand = new Random();
        int upperBound = 5;

        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("| Building new Store... |");
                System.out.println("+------------------------+");

                store.setId(Integer.toString(rand.nextInt(upperBound)));

                System.out.print("Name: ");
                store.setName(scan.nextLine());

                System.out.print("\nCity: ");
                store.setCity(scan.nextLine());

                System.out.print("\nState: ");
                store.setState(scan.nextLine());

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

    private void updateStore(){
        Scanner scan = new Scanner(System.in);
        Product product = new Product();
        Inventory inventory = new Inventory();

        int quantity;
        int q;
        String productId;

        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("| Updating Store... |");
                System.out.println("+------------------------+");

                productId = UUID.randomUUID().toString();
                product.setId(productId);
                inventory.setProductId(productId);

                System.out.print("Name: ");
                product.setName(scan.nextLine());

                System.out.print("\nCategory: ");
                product.setCategory(scan.nextLine());

                System.out.print("\nDescription: ");
                product.setDescription(scan.nextLine());

                System.out.print("\nQuantity: ");
                quantity = scan.nextInt();
                product.setQuantity(quantity);
                inventory.setQuantity(quantity);

                System.out.print("\nCost: ");
                product.setCost(scan.nextInt());

                //**//FIX ME//**//
                System.out.print("\nStore ID: ");
                inventory.setStoreId(scan.nextLine());
                //**//FIX ME//**//

                System.out.println("\nPlease confirm updates (y/n)");
                System.out.println("\n" + product);

                switch (scan.nextLine()) {
                    case "y":
                        product = new Product(productId, product.getName(), product.getCategory(), product.getDescription(), product.getQuantity(), product.getCost());
                        inventory = new Inventory(productId, inventory.getStoreId(), inventory.getQuantity());
                        productService.register(product);
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