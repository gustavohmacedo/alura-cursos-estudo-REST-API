INSERT INTO tb_usuario(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$MV35ABviP7O/UhVFoLh6I.hUsWc78jvRxciIugUHt1pBKSNe5Rq22');
INSERT INTO tb_usuario(nome, email, senha) VALUES('instrutor', 'instrutor@email.com', '$2a$10$oS0LRcsSDFdYJp/k3HVPNuPgSiukSApQn/5XxDqBpgsP..yjPsQ5u');

INSERT INTO tb_curso(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO tb_curso(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO tb_topico(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2022-02-17 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO tb_topico(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2022-02-15 19:00:00', 'SOLUCIONADO', 1, 1);
INSERT INTO tb_topico(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2022-02-14 20:00:00', 'NAO_SOLUCIONADO', 1, 2);

INSERT INTO tb_resposta(data_criacao, mensagem, solucao, autor_id, topico_id   ) VALUES('2022-02-17 20:15:00', 'Checar as dependências no Maven', 1, 2, 2)
INSERT INTO tb_resposta(data_criacao, mensagem, solucao, autor_id, topico_id   ) VALUES('2022-02-17 21:30:00', 'Talvez seja algum problema no thymeleaf', 0, 2, 3 )


