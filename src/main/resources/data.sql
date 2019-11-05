INSERT INTO BANCO (id, nome) VALUES
    (1, 'Banco Warpaint'),
    (2, 'Banco Impala'),
    (3, 'Banco Radiohead'),
    (4, 'Banco Monkeys');


INSERT INTO AGENCIA (id, codigo, endereco, telefone, banco_fk) VALUES
    (1, '1001-0','Rua dos Alfeneiros', '78166165', 1),
    (2, '1002-0','Rua Altos', '48616165', 1),
    (3, '2001-0','Rua dos DJs', '78164865', 2),
    (4, '2002-0','Rua Quixote', '78178565', 2),
    (5, '3001-0','Rua Wayman', '78781165', 3),
    (6, '3002-0','Rua Cocal', '78168865', 3);


INSERT INTO CLIENTE (id, conta, nome, saldo, agencia_fk) VALUES
    (1, '64684', 'Renato', 6000, 1),
    (2, '98888', 'Valeska', 7800, 1),
    (3, '75444', 'Felipe', 10000, 1),
    (4, '84556', 'Luis', 8500, 2),
    (5, '84135', 'Lucas', 9000, 2);