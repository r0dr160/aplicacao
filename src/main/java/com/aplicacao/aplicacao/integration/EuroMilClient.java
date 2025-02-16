package com.aplicacao.aplicacao.integration;

import euromil.EuromilGrpc;
import euromil.RegisterReply;
import euromil.RegisterRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * Cliente para interação com o serviço gRPC EuroMil.
 */
public class EuroMilClient {

    // Stub bloqueante para comunicação com o serviço gRPC
    private final EuromilGrpc.EuromilBlockingStub blockingStub;

    /**
     * Construtor que inicializa o canal gRPC e o stub para chamadas bloqueantes.
     *
     * @param host Host onde o serviço gRPC está a correr.
     * @param port Porta para comunicação gRPC.
     */
    public EuroMilClient(String host, int port) {
        // Cria o canal de comunicação com o serviço gRPC
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        // Inicializa o stub bloqueante com o canal criado
        this.blockingStub = EuromilGrpc.newBlockingStub(channel);
    }

    /**
     * Regista a aposta através do serviço gRPC.
     *
     * @param key     Chave do Euromilhões.
     * @param checkId Identificador do cheque obtido do CrediBank.
     * @return Mensagem de resposta do serviço EuroMil.
     */
    public String registerEuroMil(String key, String checkId) {
        try {
            // Cria a requisição gRPC com os parâmetros necessários
            RegisterRequest request = RegisterRequest.newBuilder()
                    .setKey(key)
                    .setCheckid(checkId)
                    .build();

            // Efetua a chamada gRPC e recebe a resposta
            RegisterReply reply = blockingStub.registerEuroMil(request);
            return reply.getMessage();

        } catch (StatusRuntimeException e) {
            // Trata exceções na comunicação gRPC
            throw new RuntimeException("Falha ao conectar ao EuroMilRegister: " + e.getMessage(), e);
        }
    }
}
