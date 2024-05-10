package me.sairu.cart.resource;

import lombok.RequiredArgsConstructor;
import me.sairu.cart.model.Cart;
import me.sairu.cart.model.Item;
import me.sairu.cart.resource.dto.ItemDto;
import me.sairu.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItemToCart(@RequestBody ItemDto itemDto){
        return cartService.addItem(itemDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cart getCartById(@PathVariable("id") Long id){
        return cartService.viewCart(id);
    }

    @PatchMapping("/close/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cart closeCartById(@PathVariable("id") Long id, @RequestParam("paymentMethod") int paymentMethod){
        return cartService.closeCart(id, paymentMethod);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalStateException(IllegalStateException e) {
        return e.getMessage();
    }
}