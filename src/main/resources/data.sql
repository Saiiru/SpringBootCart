-- Inserting into restaurant table
INSERT INTO restaurant (id, name, cep, street) VALUES
(1L, 'Restaurante 1', '0000001', 'Complemento Endereço Restaurante 1'),
(2L, 'Restaurante 2', '0000002', 'Complemento Endereço Restaurante 2'),
(3L, 'Restaurante 3', '0000003', 'Complemento Endereço Restaurante 3'),
(4L, 'Restaurante 4', '0000004', 'Complemento Endereço Restaurante 4');

-- Inserting into client table
INSERT INTO client (id, name, cep, street) VALUES
(1L, 'Cliente 1', '0000001', 'Complemento Endereço Cliente 1'),
(2L, 'Cliente 2', '0000002', 'Complemento Endereço Cliente 2'),
(3L, 'Cliente 3', '0000003', 'Complemento Endereço Cliente 3'),
(4L, 'Cliente 4', '0000004', 'Complemento Endereço Cliente 4');

-- Inserting into product table
INSERT INTO product (id, name, price, available, restaurant_id) VALUES
(1L, 'Produto 1', 5.0, true, 1L),
(2L, 'Produto 2', 6.0, true, 1L),
(3L, 'Produto 3', 7.0, true, 2L),
(4L, 'Produto 4', 8.0, true, 2L),
(5L, 'Produto 5', 9.0, true, 3L),
(6L, 'Produto 6', 10.0, true, 3L),
(7L, 'Produto 7', 11.0, true, 4L),
(8L, 'Produto 8', 12.0, true, 4L);

-- Inserting into cart table
INSERT INTO cart (id, payment_form, closed, total_price, client_id) VALUES
(1L, 0, false, 0.0, 1L);