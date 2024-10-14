# Simples Dental️👨‍💻

![Version](https://img.shields.io/badge/version-0.0.1-blue)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white)

É uma aplicação de gerenciamento do controle de cadastro de profissionais e seus números de contato.

## Funcionalidades
- Gerenciamento de profissionais (adicionar, atualizar, buscar, listar e excluir)
- Gerenciamento de contatos (adicionar, atualizar, buscar, listar e excluir)

## Infraestrutura

É uma arquitetura monolítica baseada nos conceitos do Domain-Driven Design (DDD). O sistema está dividido em duas camadas 
principais: domain e infrastructure. Na camada domain está todo o código relacionado ao domínio da aplicação, como as 
entidades, objetos de transferência (DTOs) e a interface com os métodos que o serviço da entidade deve expor. Na camada
infrastructure está toda a lógica que contém algum tipo de dependência de bibliotecas.

Além disso, a aplicação faz uso de containers Docker para instanciar o banco de dados PostgreSQL.

## Documentação

O uso Swagger simplifica o processo de documentação e teste da API. Ele fornece uma interface visual que permite
visualizar e interajam com a API sem a necessidade de entender os detalhes técnicos subjacentes.

Toda a documentação da API, foi construída utilizando Swagger com uso de configurações customizadas para detalhar e espeficicar cada
endpoint disponível.

Após executar a aplicação, o swagger estará disponível em: http://localhost:8080/swagger-ui/index.html#

## Principais tecnologias
- [Spring Boot](https://spring.io/projects/spring-boot): O Spring Boot é um framework de desenvolvimento em Java que
  simplifica a criação de aplicativos web e microserviços, fornecendo configuração e estrutura pré-definidas.
- [Maven](https://maven.apache.org/): É uma ferramenta de automação de construção e gerenciamento de dependências
  usada principalmente em projetos Java. Ele simplifica o processo de compilação, empacotamento e distribuição de aplicativos.
- [PostgreSQL](https://www.postgresql.org/): É um sistema de gerenciamento de banco de dados relacional de código aberto, usado
  para armazenar, recuperar e gerenciar dados. Ele oferece recursos avançados, é altamente extensível e amplamente
  utilizado em uma variedade de aplicativos..
- [Docker](https://www.docker.com/): É uma plataforma de virtualização de contêineres que permite empacotar
  aplicativos e suas dependências em contêineres isolados.
- [Swagger](https://swagger.io/): Simplifica a documentação e teste de APIs, permitindo aos desenvolvedores descrever,
  visualizar e interagir com serviços da web de forma eficiente.
- [Lombok](https://projectlombok.org/):  É uma biblioteca que simplifica o código Java ao reduzir a verbosidade, 
  principalmente na criação de getters, setters, construtores e métodos comuns.
- [MapStruct](https://mapstruct.org/): É uma biblioteca Java que facilita a conversão automática de objetos entre classes, como DTOs e 
  entidades de banco de dados.
- [Flyway](https://www.baeldung.com/database-migrations-with-flyway): É uma ferramenta de versionamento de banco de 
  dados, utilizada para gerenciar e aplicar migrações de forma automática.

## Requisitos
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Execução

1. Execute os containers docker:
```bash
docker-compose up -d
```

2. Aguarde até que todos os contêineres estejam online.

## Feedback

Se você tiver algum feedback, por favor nos deixe saber por meio de dev.igorpimentel@gmail.com

## Autores

- [LinkedIn: Igor Pimentel](https://www.linkedin.com/in/igor-pimentel-g/)