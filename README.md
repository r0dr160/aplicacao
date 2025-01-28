# Sistema de Apostas - EuroMil

Este projeto é uma aplicação integradora que **simula** o registro de apostas online em um jogo similar ao Euromilhões. Ele cumpre os seguintes requisitos:

1. Permite ao usuário inserir:
    - A **chave** (números do Euromilhões).
    - A **identificação de conta** no sistema **CrediBank** (16 dígitos).

2. **Contacta** o sistema externo **CrediBank** (API REST) para gerar um cheque digital no valor de 10 créditos (usado para pagar a aposta).

3. **Contacta** o sistema externo **EuroMilRegister** (via **gRPC**) para efetivar o registro da aposta, enviando:
    - A chave Euromilhões.
    - O cheque digital retornado pelo CrediBank.

4. **Exibe** mensagem de **sucesso** ou **falha**:
    - Se houver erro de conexão ou indisponibilidade dos serviços externos, informa o usuário claramente.

## Tecnologias e Dependências

- **Java 17** (pode ser outra versão suportada pelo Spring Boot 3.x).
- **Spring Boot 3.1.x** (Web + Thymeleaf).
- **gRPC**: via bibliotecas `io.grpc:grpc-stub, grpc-protobuf, grpc-netty-shaded`.
- **Protobuf**: para gerar classes de mensagens do `.proto`.
- **Thymeleaf**: para renderizar as páginas HTML do formulário e resultado.


## Como Executar Localmente

1. **Clonar** ou **baixar** o projeto.
2. Ter instalado **Java 17** (ou compatível) e **Gradle** (opcional se usar o wrapper `gradlew`).
3. Ajustar (se necessário) o **host** e **porta** do gRPC (no `EuroMilClient.java`) e a URL do **CrediBank** (no `CrediBankClient.java`), caso sejam diferentes do enunciado.

### Passos no Terminal

- `./gradlew clean build` (ou no Windows `gradlew.bat clean build`) para compilar.
- `./gradlew bootRun` para rodar a aplicação localmente.
- Acesse **<http://localhost:8080>** no seu navegador.


## Arquitetura Simplificada

1. **Utilizador** → [Formulário HTML / Thymeleaf] → **`ApostaController`**
2. **Controller** → [REST] → **`CrediBankClient`** → `GET https://credibank...` → Retorna `checkID`
3. **Controller** → [gRPC] → **`EuroMilClient`** → `registerEuroMil(key, checkId)` → Retorna mensagem
4. **Controller** → Exibe resultado em `resultado.html`.

## Autor
- **Rodrigo Costa**
    - Email: 2401857@estudante.uab.pt
    - Universidade Aberta

---

## Licença
Este projeto é de uso educacional e desenvolvido como parte do Mestrado em Engenharia Informática da Universidade Aberta.



