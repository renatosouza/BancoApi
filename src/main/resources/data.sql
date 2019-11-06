INSERT INTO BANCO (nome) VALUES
    ('Banco Warpaint'),
    ('Banco Impala'),
    ('Banco Radiohead'),
    ('Banco Monkeys');


INSERT INTO AGENCIA (codigo, endereco, telefone, banco_fk) VALUES
    ('1001-0','Rua dos Alfeneiros', '78166165', 1),
    ('1002-0','Rua Altos', '48616165', 1),
    ('2001-0','Rua dos DJs', '78164865', 2),
    ('2002-0','Rua Quixote', '78178565', 2),
    ('3001-0','Rua Wayman', '78781165', 3),
    ('3002-0','Rua Cocal', '78168865', 3);


INSERT INTO CLIENTE (conta, nome, saldo, agencia_fk) VALUES
    ('64684', 'Renato', 6000, 1),
    ('98888', 'Valeska', 7800, 1),
    ('75444', 'Felipe', 10000, 1),
    ('84556', 'Luis', 8500, 2),
    ('84135', 'Lucas', 9000, 2);