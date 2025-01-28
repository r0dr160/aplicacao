package com.aplicacao.aplicacao.integration;

import euromil.EuromilGrpc;
import euromil.RegisterReply;
import euromil.RegisterRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class EuroMilClient {

    private final EuromilGrpc.EuromilBlockingStub blockingStub;

    public EuroMilClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        this.blockingStub = EuromilGrpc.newBlockingStub(channel);
    }

    public String registerEuroMil(String key, String checkId) {
        try {
            RegisterRequest request = RegisterRequest.newBuilder()
                    .setKey(key)
                    .setCheckid(checkId)
                    .build();

            RegisterReply reply = blockingStub.registerEuroMil(request);
            return reply.getMessage();

        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Falha ao conectar ao EuroMilRegister: " + e.getMessage(), e);
        }
    }
}
