package org.gestionlog.usecase;

import org.gestionlog.r2dbc.postgresql.gateways.TipoProductosAdapterRepository;
import org.gestionlog.r2dbc.postgresql.gateways.TipoProductosUseCaseRepository;
import org.gestionlog.r2dbc.postgresql.model.TipoProductos;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class TipoProductosUseCase implements TipoProductosUseCaseRepository {

    private final TipoProductosAdapterRepository TipoProductosAdapterRepository;

    public TipoProductosUseCase(TipoProductosAdapterRepository tipoProductosAdapterRepository){
        this.TipoProductosAdapterRepository = tipoProductosAdapterRepository;
    }


    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
        return  request.bodyToMono(TipoProductos.class).log()
                .flatMap(prod ->TipoProductosAdapterRepository.create(prod)).log()
                .flatMap(prodCreate -> ServerResponse.ok().bodyValue(prodCreate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return  request.bodyToMono(TipoProductos.class).log()
                .flatMap(prod ->TipoProductosAdapterRepository.update(prod))
                .flatMap(prodUpdate -> ServerResponse.status(202).bodyValue(prodUpdate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        return
                TipoProductosAdapterRepository.delete(request.pathVariable("clientId"))
                        .then()
                        .then(ServerResponse.noContent().build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
    }

    @Override
    public Mono<ServerResponse> findTipoProductosById(ServerRequest request) {
        return  TipoProductosAdapterRepository.findTipoProductosById(request.pathVariable("clientId"))
                .flatMap(product ->ServerResponse.ok().bodyValue(product))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> findAllTipoProductos(ServerRequest request) {
        return  TipoProductosAdapterRepository.findAllTipoProductos()
                .collectList()
                .flatMap(allProducts ->ServerResponse.ok().bodyValue(allProducts))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }
}