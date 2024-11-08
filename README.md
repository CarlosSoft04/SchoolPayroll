# Sistema de Folha de Pagamento para Professores

Este repositório contém um sistema de folha de pagamento desenvolvido em Java com Spring Boot, focado no gerenciamento de informações de pagamento de professores de uma escola. O sistema utiliza um banco de dados H2 em memória, acessado via JPA, e exposto por meio de uma API RESTful com suporte a HATEOAS.

## 📋 Funcionalidades

- **Cadastro e gerenciamento de professores**: Permite a criação, leitura, atualização e exclusão de dados de professores.
- **Processamento de folha de pagamento**: Gerencia os detalhes de pagamento dos professores de forma prática e acessível.
- **APIs RESTful com HATEOAS**: A aplicação segue o estilo REST, permitindo navegação entre recursos.
- **Banco de dados H2**: Utiliza um banco de dados em memória para desenvolvimento rápido e testes.

## 🚀 Tecnologias Utilizadas

- **Java+**
- **Spring Boot 2.x**
  - Spring MVC
  - Spring Data JPA
  - Spring HATEOAS
- **Banco de Dados H2 (em memória)**

## 📂 Estrutura do Projeto

A estrutura do projeto segue uma divisão em camadas:

- **Controller**: Gerencia as requisições e respostas da API
- **Assembler**: Transforma dados de entidades em respostas REST.
- **Util**:Armazena funções utilitárias usadas em todo o projeto.
- **Model**: Define as entidades e mapeamentos de dados.
- **Error**: Lida com o tratamento e retorno de erros.
- **Repository**:  Fornece acesso e manipulação de dados no banco.

## ⚙️ Instalação e Execução

1. Clone o repositório:
