package com.revature.customPaint.services;

import com.revature.customPaint.daos.CartDAO;
import com.revature.customPaint.models.Cart;

import java.util.List;

public class CartService {
    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public void register(Cart cart) {
        cartDAO.save(cart);
    }

    public List<Cart> getAllOrderedItems() {
        return cartDAO.getAll();
    }
}
