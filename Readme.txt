DEV COINS API

API REST desenvolvida em Java com Spring Boot para controle interno
entre desenvolvedores, utilizada para gerenciamento de interações e
regras de negócio internas.

O projeto foi estruturado com foco em organização de camadas, boas
práticas de desenvolvimento backend e separação clara entre Controller,
Service e Repository.

Observação: O contexto real de uso envolve regras internas e informações
sensíveis, portanto os detalhes de negócio foram abstraídos.

  --------
  DEPLOY
  --------

A aplicação está publicada em: https://dev-coins.onrender.com/

Como o deploy está no plano gratuito do Render, a aplicação pode levar
cerca de 1 a 2 minutos para iniciar após um período de inatividade (cold
start).

Banco de dados hospedado em Neon (PostgreSQL serverless).

  ------------------------
  TECNOLOGIAS UTILIZADAS
  ------------------------

-   Java 17+
-   Spring Boot
-   Spring Web (REST)
-   Spring Data JPA
-   Hibernate
-   PostgreSQL (Neon)
-   Maven
-   Git
-   Deploy em Render

  -------------
  ARQUITETURA
  -------------

O projeto segue arquitetura em camadas:

-   Controller – Exposição dos endpoints REST
-   Service – Regras de negócio
-   Repository – Acesso a dados com JPA
-   DTO – Objetos de transporte de dados

Separação clara de responsabilidades, evitando lógica de negócio na
camada de controller.

  -----------------
  FUNCIONALIDADES
  -----------------

-   CRUD completo
-   Transferência interna entre registros
-   Validações de regra de negócio
-   Tratamento adequado de respostas HTTP
-   Uso de Stream API para critérios de comparação
-   Estrutura preparada para evolução

  ----------------------
  ENDPOINTS PRINCIPAIS
  ----------------------

Criar registro POST /api/devs

Listar todos GET /api/devs

Buscar por ID GET /api/devs/{id}

Atualizar PUT /api/devs/{id}

Transferência interna POST /api/devs/transferir

Remover DELETE /api/devs/{id}

  ---------------------
  CONCEITOS APLICADOS
  ---------------------

-   RESTful API design
-   Separação de responsabilidades
-   Validação de regras de negócio na camada Service
-   Uso de Optional
-   ResponseEntity
-   Stream API
-   Clean Code
-   Deploy em ambiente cloud
-   Banco de dados serverless
