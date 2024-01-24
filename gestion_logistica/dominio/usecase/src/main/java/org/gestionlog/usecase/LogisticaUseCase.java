package org.gestionlog.usecase;

import org.gestionlog.r2dbc.postgresql.gateways.LogisticaAdapterRepository;
import org.gestionlog.r2dbc.postgresql.gateways.LogisticaUseCaseRepository;
import org.gestionlog.r2dbc.postgresql.model.Logistica;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class LogisticaUseCase implements LogisticaUseCaseRepository {

    private final LogisticaAdapterRepository LogisticaAdapterRepository;

    public LogisticaUseCase(LogisticaAdapterRepository LogisticaAdapterRepository){
        this.LogisticaAdapterRepository = LogisticaAdapterRepository;
    }


    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
        return  request.bodyToMono(Logistica.class).log()
                .flatMap(prod ->LogisticaAdapterRepository.create(prod)).log()
                .flatMap(prodCreate -> ServerResponse.ok().bodyValue(prodCreate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return  request.bodyToMono(Logistica.class).log()
                .flatMap(prod ->LogisticaAdapterRepository.update(prod))
                .flatMap(prodUpdate -> ServerResponse.status(202).bodyValue(prodUpdate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        return
                LogisticaAdapterRepository.delete(request.pathVariable("clientId"))
                        .then()
                        .then(ServerResponse.noContent().build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
    }

    @Override
    public Mono<ServerResponse> findLogisticaById(ServerRequest request) {
        return  LogisticaAdapterRepository.findLogisticaById(request.pathVariable("clientId"))
                .flatMap(product ->ServerResponse.ok().bodyValue(product))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> findAllLogistica(ServerRequest request) {
        return  LogisticaAdapterRepository.findAllLogistica()
                .collectList()
                .flatMap(allProducts ->ServerResponse.ok().bodyValue(allProducts))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }
}