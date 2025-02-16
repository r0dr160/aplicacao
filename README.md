# Sistema de Apostas - EuroMil

Este projeto é uma aplicação integradora que **simula** o registo de apostas online num jogo similar ao Euromilhões.
O sistema cumpre os seguintes requisitos:

1. Permite ao utilizador inserir:
    - A **chave** (números do Euromilhões).
    - A **identificação de conta** no sistema **CrediBank** (16 dígitos).

2. Contacta o sistema externo **CrediBank** (API REST) para gerar um cheque digital no valor de 10 créditos (usado para pagar a aposta).

3. Contacta o sistema externo **EuroMilRegister** (via **gRPC**) para efetivar o registo da aposta, enviando:
    - A chave Euromilhões.
    - O cheque digital retornado pelo CrediBank.

4. Exibe mensagem de **sucesso** ou **falha**:
    - Se houver erro de conexão ou indisponibilidade dos serviços externos, informa o utilizador de forma clara.

## Tecnologias e Dependências

- **Java 17** (ou outra versão suportada pelo Spring Boot 3.x).
- **Spring Boot 3.1.x** (Web + Thymeleaf).
- **gRPC**: com as bibliotecas `io.grpc:grpc-stub`, `grpc-protobuf`, `grpc-netty-shaded`.
- **Protobuf**: para geração das classes a partir dos ficheiros `.proto`.
- **Thymeleaf**: para renderização das páginas HTML (formulário e resultado).

## Como Executar Localmente

1. **Clonar** ou **baixar** o projeto.
2. Ter instalado o **Java 17** (ou versão compatível) e o **Gradle** (opcional, pois existe o wrapper `gradlew`).
3. Ajustar (se necessário) o host e a porta do gRPC (no ficheiro `EuroMilClient.java`) e a URL do CrediBank (no ficheiro `CrediBankClient.java`).

### Passos no Terminal

- Execute `./gradlew clean build` (ou, no Windows, `gradlew.bat clean build`) para compilar o projeto.
- Execute `./gradlew bootRun` para iniciar a aplicação.
- Abra o navegador e aceda a **http://localhost:8080**.

## Arquitetura Simplificada

1. **Utilizador** → [Formulário HTML / Thymeleaf] → **`ApostaController`**
2. **Controller** → [REST] → **`CrediBankClient`** → `GET https://credibank...` → Retorna `checkID`
3. **Controller** → [gRPC] → **`EuroMilClient`** → `registerEuroMil(key, checkId)` → Retorna mensagem
4. **Controller** → Exibe o resultado na página `resultado.html`.

## Documentação de Código

Para uma manutenção e avaliação de qualidade, todos os ficheiros de implementação e de configuração estão devidamente comentados com javadocs e comentários em linha, explicando a funcionalidade de cada componente e os fluxos de execução da aplicação.

## Lista de Ficheiros com Documentação Relevante

- **Aplicação e Configuração:**
    - `AplicacaoApplication.java`
    - `GrpcConfig.java`
    - `ApostaController.java`
    - `ApostaForm.java`
    - `EuromilService.java`
- **Integração com Serviços Externos:**
    - `CrediBankCheckResponse.java`
    - `CrediBankClient.java`
    - `EuroMilClient.java`
    - `IntegrationException.java`
- **Comunicação via gRPC:**
    - `euromil.proto` (definição dos serviços e mensagens)
    - Ficheiros gerados automaticamente a partir do .proto:
        - `EuromilGrpc.java`
        - `EuroMilProto.java`
        - `RegisterReply.java`
        - `RegisterRequest.java`
- **Configuração do Build:**
    - `build.gradle`

## Autor
- **Rodrigo Costa**
    - Email: 2401857@estudante.uab.pt
    - Universidade Aberta

---

## Licença

Este projeto é de uso educacional e foi desenvolvido como parte do Mestrado em Engenharia Informática da Universidade Aberta.
