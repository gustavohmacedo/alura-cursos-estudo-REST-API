# Fórum REST API  :computer:

   

- Trata-se de um Fórum Tira-Dúvidas, onde alunos cadastram tópicos de suas respectivas dúvidas de uma tecnologia estudada ;

- Tópico - Contém os seguintes campos: título, mensagem, dataCriacao, statusTopico, autor, curso e uma lista de respostas;

- StatusTopico - É um Enum e contém tais valores fixos predefinidos e imutáveis: NAO_RESPONDIDO, NAO_SOLUCIONADO, SOLUCIONADO, FECHADO;

- Autor [usuário logado na plataforma] - Contém os seguintes campos: nome, e-mail e senha;      

- Curso - Contém os seguintes campos: nome e categoria.

- Resposta - Contém os seguintes campos: mensagem, tópico, dataCriaco, autor e solucao.

  

   Aplicações utilizadas :gear:

- Java 8 LTS

- Gerenciador de Dependências: Maven

- IDE: Intellij community

- Project Lombok

- Database H2:  http://localhost:8080/h2-forum

-  Link da Documentação da aplicação no Swagger: http://localhost:8080/swagger-ui.html

  

