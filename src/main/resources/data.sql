
INSERT INTO receita (descricao,valor,data) values ('Salario',7800.00,'2021-12-01');
INSERT INTO receita (descricao,valor,data) values ('Salario',7300.00,'2022-01-01');
INSERT INTO receita (descricao,valor,data) values ('Salario Extra',450.00,'2022-01-01');
INSERT INTO receita (descricao,valor,data) values ('Vendas ML',500.00,'2022-01-01');
INSERT INTO receita (descricao,valor,data) values ('Salario',7800.00,'2022-02-01');

INSERT INTO despesa (descricao,valor,data,categoria) values ('Gas',180.00,'2022-01-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',90.00,'2022-01-01', 'OUTRAS');

INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',70.00,'2021-01-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',72.00,'2021-02-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',74.00,'2021-03-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',75.00,'2021-04-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',75.00,'2021-05-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',76.00,'2021-06-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',76.00,'2021-07-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',80.00,'2021-08-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',82.00,'2021-09-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',85.00,'2021-10-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',87.00,'2021-11-01', 'OUTRAS');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Agua',87.00,'2021-12-01', 'OUTRAS');

INSERT INTO despesa (descricao,valor,data,categoria) values ('Futsal semanal', 60.00,'2022-01-01', 'LAZER');
INSERT INTO despesa (descricao,valor,data,categoria) values ('Bola de futsal', 120.00,'2022-01-01', 'LAZER');


INSERT INTO perfil (nome) values ('ROLE_ADMIN');
INSERT INTO perfil (nome) values ('ROLE_GUEST');

--senha 123456 
INSERT INTO usuario (nome, email, senha) values ('Felipe Volpato', 'fvolpato@gmail.com', 
'$2a$12$bmCCocIcuLQC/j/i/IcamOfrjURmo1oYSTkRJRsR2DTk.a5BqCfCO');

--senha guest
INSERT INTO usuario (nome, email, senha) values ('guest', 'guest@gmail.com', 
'$2a$12$JupjDKdi7snLCwtm8mQdiuXCal9s2D4ngTfcQb5EmF5ugba4eK7.6');

INSERT INTO usuario_perfis (usuario_id, perfis_id) values (1,1);
INSERT INTO usuario_perfis (usuario_id, perfis_id) values (1,2);
INSERT INTO usuario_perfis (usuario_id, perfis_id) values (2,2);



