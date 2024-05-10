package me.sairu.cart.service.impl;

import lombok.RequiredArgsConstructor;
import me.sairu.cart.enums.PaymentForm;
import me.sairu.cart.model.Cart;
import me.sairu.cart.model.Item;
import me.sairu.cart.model.Restaurant;
import me.sairu.cart.repository.CartRepository;
import me.sairu.cart.repository.ItemRepository;
import me.sairu.cart.repository.ProductRepository;
import me.sairu.cart.resource.dto.ItemDto;
import me.sairu.cart.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
public Item addItem(ItemDto itemDto) {
    Cart cart = cartRepository.findById(itemDto.getCartId())
            .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + itemDto.getCartId()));

    if (cart.isClosed()) {
        throw new IllegalStateException("Cart is already closed");
    }

    Item itemForInsert = Item.builder()
            .cart(cart)
            .quantity(itemDto.getQuantity())
            .productId(productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + itemDto.getProductId())))
            .build();

    Optional<Item> existingItem = cart.getItems().stream()
            .filter(item -> item.getProductId().getId().equals(itemForInsert.getProductId().getId()))
            .findFirst();

    if (existingItem.isPresent()) {
        // If the item already exists in the cart, update the quantity
        existingItem.get().setQuantity(existingItem.get().getQuantity() + itemDto.getQuantity());
        return itemRepository.save(existingItem.get());
    } else {
        // If the item doesn't exist in the cart, add the new item
        cart.getItems().add(itemForInsert);
        cartRepository.save(cart);
        return itemRepository.save(itemForInsert);
    }
}

    @Override
    public Cart viewCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
    }

    @Override
    public Cart closeCart(Long cartId, int paymentMethodNumber) {
        Cart cart = viewCart(cartId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        PaymentForm paymentForm = paymentMethodNumber == 0 ? PaymentForm.CASH : PaymentForm.CREDIT_CARD;
        cart.setPaymentForm(paymentForm);
        cart.setClosed(true);

        return cartRepository.save(cart);
    }


}