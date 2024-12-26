INSERT INTO VS_15_EQUIPE_2.CLIENTE (id_cliente, nome, email, telefone)
VALUES (VS_15_EQUIPE_2.SEQ_CLIENTE.NEXTVAL, 'Fábio Bastian', 'fabiobastian44@gmail.com', '51998877665');

INSERT INTO VS_15_EQUIPE_2.CLIENTE (id_cliente, nome, email, telefone)
VALUES (VS_15_EQUIPE_2.SEQ_CLIENTE.NEXTVAL, 'Ana Souza', 'anasouza@gmail.com', '51987766544');


INSERT INTO VS_15_EQUIPE_2.ENDERECO (id_endereco, id_cliente, logradouro, numero, bairro, cidade, cep)
VALUES (VS_15_EQUIPE_2.SEQ_ENDERECO.NEXTVAL, 1, 'Rua A', '123', 'Centro', 'Porto Alegre', '90000000');

INSERT INTO VS_15_EQUIPE_2.ENDERECO (id_endereco, id_cliente, logradouro, numero, bairro, cidade, cep)
VALUES (VS_15_EQUIPE_2.SEQ_ENDERECO.NEXTVAL, 2, 'Rua B', '456', 'Bairro X', 'Caxias do Sul', '95000000');


INSERT INTO VS_15_EQUIPE_2.PRODUTO (id_produto, nome, descricao, preco, tamanho, cor, estoque)
VALUES (VS_15_EQUIPE_2.SEQ_PRODUTO.NEXTVAL, 'Tênis Running', 'Tênis para corrida', 250.00, 42, 'Preto', 10);

INSERT INTO VS_15_EQUIPE_2.PRODUTO (id_produto, nome, descricao, preco, tamanho, cor, estoque)
VALUES (VS_15_EQUIPE_2.SEQ_PRODUTO.NEXTVAL, 'Bota Adventure', 'Bota para trilha', 300.00, 44, 'Marrom', 5);


INSERT INTO VS_15_EQUIPE_2.PEDIDO (id_pedido, data, total, status_pedido)
VALUES (VS_15_EQUIPE_2.SEQ_PEDIDO.NEXTVAL, TO_DATE('2024-12-24', 'YYYY-MM-DD'), 550.00, 1);

INSERT INTO VS_15_EQUIPE_2.PEDIDO (id_pedido, data, total, status_pedido)
VALUES (VS_15_EQUIPE_2.SEQ_PEDIDO.NEXTVAL, TO_DATE('2024-12-23', 'YYYY-MM-DD'), 600.00, 2);


INSERT INTO VS_15_EQUIPE_2.ITEM_PEDIDO (id_item_pedido, id_pedido, id_produto, quantidade)
VALUES (VS_15_EQUIPE_2.SEQ_ITEM_PEDIDO.NEXTVAL, 1, 1, 2);

INSERT INTO VS_15_EQUIPE_2.ITEM_PEDIDO (id_item_pedido, id_pedido, id_produto, quantidade)
VALUES (VS_15_EQUIPE_2.SEQ_ITEM_PEDIDO.NEXTVAL, 2, 2, 1);


INSERT INTO VS_15_EQUIPE_2.PAGAMENTO (id_pagamento, id_pedido, valor, forma_pagamento)
VALUES (VS_15_EQUIPE_2.SEQ_PAGAMENTO.NEXTVAL, 1, 550.00, 1);

INSERT INTO VS_15_EQUIPE_2.PAGAMENTO (id_pagamento, id_pedido, valor, forma_pagamento)
VALUES (VS_15_EQUIPE_2.SEQ_PAGAMENTO.NEXTVAL, 2, 600.00, 2);
