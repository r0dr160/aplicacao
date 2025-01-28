package com.aplicacao.aplicacao.service;

import euromil.EuromilGrpc;
import euromil.RegisterReply;
import euromil.RegisterRequest;
import io.grpc.stub.StreamObserver;

public class EuromilService extends EuromilGrpc.EuromilImplBase {

    @Override
    public void registerEuroMil(RegisterRequest request, StreamObserver<RegisterReply> responseObserver) {
        // Simulação de sucesso
        RegisterReply reply = RegisterReply.newBuilder()
                .setMessage("Aposta registrada com sucesso!")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}