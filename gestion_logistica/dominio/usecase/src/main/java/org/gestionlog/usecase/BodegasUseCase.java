package org.gestionlog.usecase;

import org.gestionlog.r2dbc.postgresql.gateways.BodegasUseCaseRepository;
import org.gestionlog.r2dbc.postgresql.gateways.BodegasAdapterRepository;
import org.gestionlog.r2dbc.postgresql.model.Bodegas;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class BodegasUseCase implements BodegasUseCaseRepository {

    private final BodegasAdapterRepository BodegasAdapterRepository;

    public BodegasUseCase(BodegasAdapterRepository BodegasAdapterRepository){
        this.BodegasAdapterRepository = BodegasAdapterRepository;
    }


    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
        return  request.bodyToMono(Bodegas.class).log()
                .flatMap(prod ->BodegasAdapterRepository.create(prod)).log()
                .flatMap(prodCreate -> ServerResponse.ok().bodyValue(prodCreate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return  request.bodyToMono(Bodegas.class).log()
                .flatMap(prod ->BodegasAdapterRepository.update(prod))
                .flatMap(prodUpdate -> ServerResponse.status(202).bodyValue(prodUpdate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        return
                BodegasAdapterRepository.delete(request.pathVariable("clientId"))
                        .then()
                        .then(ServerResponse.noContent().build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
    }

    @Override
    public Mono<ServerResponse> findBodegasById(ServerRequest request) {
        return  BodegasAdapterRepository.findBodegasById(request.pathVariable("clientId"))
                .flatMap(product ->ServerResponse.ok().bodyValue(product))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> findAllBodegas(ServerRequest request) {
        return  BodegasAdapterRepository.findAllBodegas()
                .collectList()
                .flatMap(allProducts ->ServerResponse.ok().bodyValue(allProducts))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }
}