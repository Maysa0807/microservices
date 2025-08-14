## Microservice - Email Service

Serviço de envio de e-mails desenvolvido em Spring Boot, parte de uma arquitetura de microsserviços.

Ele é responsável por receber mensagens de outros serviços via RabbitMQ e enviar e-mails (por exemplo, notificações de cadastro de usuário).
Este serviço foi projetado para funcionar de forma assíncrona.

### Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Mail
- RabbitMQ (mensageria assíncrona)
- PostgreSQL (persistência dos e-mails enviados)
- JUnit 5 e Mockito (testes unitários)

### Clonar o repositório
```bash
git clone https://github.com/Maysa0807/microservices.git
cd microservices/email
```

### Fluxo de funcionamento

- O serviço hp-personagens```(https://github.com/Maysa0807/hp-personagens)``` publica uma mensagem de cadastro na fila RabbitMQ.
- O Email Service (este projeto) consome essa mensagem.
- O serviço processa e envia o e-mail usando o Spring Mail.
- O status (ENVIADO ou ERRO) é salvo no PostgreSQL.

  <img width="1338" height="588" alt="Image" src="https://github.com/user-attachments/assets/da5b37fd-b4e1-4c9f-9f80-4c453f77a0fd" />
