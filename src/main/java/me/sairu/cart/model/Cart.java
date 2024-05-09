package me.sairu.cart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sairu.cart.enums.PaymentForm;

import java.util.List;

/**
 * Represents a shopping cart in an e-commerce application.
 * The cart is associated with a client and contains a list of items.
 * It also keeps track of the total price of the items in the cart.
 * The payment form for the cart can be set, and the cart can be marked as closed.
 */
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Cart {
    /**
     * The unique identifier of the cart.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The client associated with the cart.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Client client;

    /**
     * The items in the cart.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    /**
     * The total price of the items in the cart.
     */
    private Double totalPrice;

    /**
     * The form of payment for the cart.
     */
    @Enumerated
    private PaymentForm paymentForm;

    /**
     * Whether the cart is closed or not.
     */
    private boolean closed;
}