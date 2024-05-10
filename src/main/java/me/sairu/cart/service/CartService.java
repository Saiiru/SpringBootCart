package me.sairu.cart.service;


import me.sairu.cart.model.Cart;
import me.sairu.cart.model.Item;
import me.sairu.cart.resource.dto.ItemDto;

public interface CartService {
    Item addItem(ItemDto itemDto);
    Cart viewCart(Long cartId);
    Cart closeCart(Long cartId, int paymentMethod);
}
