package com.aplicacao.aplicacao.service;

import euromil.EuromilGrpc;
import euromil.RegisterReply;
import euromil.RegisterRequest;
import io.grpc.stub.StreamObserver;

/**
 * Implementação do serviço gRPC para registo de apostas.
 */
public class EuromilService extends EuromilGrpc.EuromilImplBase {

    /**
     * Método gRPC que regista uma aposta.
     *
     * @param request           Requisição com os dados da aposta.
     * @param responseObserver  Observer para envio da resposta.
     */
    @Override
    public void registerEuroMil(RegisterRequest request, StreamObserver<RegisterReply> responseObserver) {
        // Simulação de sucesso na operação de registo de aposta
        RegisterReply reply = RegisterReply.newBuilder()
                .setMessage("Aposta registrada com sucesso!")
                .build();
        // Envia a resposta para o cliente
        responseObserver.onNext(reply);
        // Finaliza o envio da resposta
        responseObserver.onCompleted();
    }
}
