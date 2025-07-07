# Study Tracker 📚

Sistema de gerenciamento de sessões de estudo com tecnologias específicas. Ideal para acompanhar o progresso dos estudos técnicos.

## 🧩 Funcionalidades

- CRUD de sessões de estudo
- Associação entre sessões e tecnologias
- Validação de dados (ex: data não pode ser futura)
- Camada de DTOs para abstração da API
- Persistência com JPA + PostgreSQL

## 🛠 Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **JUnit 5**
- **Mockito**

## ▶️ Como rodar localmente

1. **Clone o projeto**

```bash
git clone https://github.com/jhops10/study-tracker.git
cd study-tracker
```

2. **Configure o banco de dados MySQL**

Crie um banco chamado study_tracker e ajuste as credenciais no application.properties:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5433/study_tracker
spring.datasource.username=root
spring.datasource.password=senha
```

3. **Rode a aplicação**
   
```bash
mvn spring-boot:run
```

3. **Endpoints disponíveis**
   
 **Study Session (`/api/v1/sessions`)**
 
| Método | Endpoint                 | Descrição                          |
|--------|--------------------------|------------------------------------|
| GET    | `/api/v1/sessions`       | Lista todas as sessões de estudo   |
| GET    | `/api/v1/sessions/{id}`  | Busca uma sessão específica        |
| POST   | `/api/v1/sessions`       | Cria uma nova sessão               |
| PUT    | `/api/v1/sessions/{id}`  | Atualiza uma sessão existente      |
| DELETE | `/api/v1/sessions/{id}`  | Remove uma sessão                  |

 **Technology (`/api/v1/technologies`)**
 
| Método | Endpoint                      | Descrição                              |
|--------|-------------------------------|----------------------------------------|
| GET    | `/api/v1/technologies`        | Lista todas as tecnologias             |
| GET    | `/api/v1/technologies/{id}`   | Busca uma tecnologia específica        |
| POST   | `/api/v1/technologies`        | Cria uma nova tecnologia               |
| PUT    | `/api/v1/technologies/{id}`   | Atualiza uma tecnologia existente      |
| DELETE | `/api/v1/technologies/{id}`   | Remove uma tecnologia                  |

## 🧪 Testes

Os testes são implementados utilizando JUnit e Mockito, cobrindo os principais serviços e controladores da aplicação.

```bash
mvn test
```

## 📌 Roadmap (futuro)

- [ ] Autenticação via JWT
- [ ] Integração com Swagger (documentação da API)
- [ ] Testes de integração com Testcontainers
- [ ] Deploy com Docker e Traefik
- [ ] Monitoramento com Spring Boot Actuator
   
