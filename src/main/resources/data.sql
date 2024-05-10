INSERT INTO restaurant (id, name, cep, street) VALUES
(1L, 'Restaurante 1', '0000001', 'Complemento Endereço Restaurante 1'),
(2L, 'Restaurante 2', '0000002', 'Complemento Endereço Restaurante 2');

INSERT INTO client (id, name, cep, street) VALUES
(1L, 'Cliente 1', '0000001', 'Complemento Endereço Cliente 1');

INSERT INTO product (id, name, price, available, restaurant_id) VALUES
(1L, 'Produto 1', 5.0, true, 1L),
(2L, 'Produto 2', 6.0, true, 2L),
(3L, 'Produto 3', 7.0, true, 2L);

INSERT INTO cart (id, payment_form, closed, total_price, client_id) VALUES
(1L, 0, false, 0.0, 1L);