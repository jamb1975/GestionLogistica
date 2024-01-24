package org.gestionlog.usecase;

import org.gestionlog.r2dbc.postgresql.gateways.PuertosMaritimosAdapterRepository;
import org.gestionlog.r2dbc.postgresql.gateways.PuertosMaritimosUseCaseRepository;
import org.gestionlog.r2dbc.postgresql.model.PuertosMaritimos;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PuertosMaritimosUseCase implements PuertosMaritimosUseCaseRepository {

    private final PuertosMaritimosAdapterRepository PuertosMaritimosAdapterRepository;

    public PuertosMaritimosUseCase(PuertosMaritimosAdapterRepository PuertosMaritimosAdapterRepository){
        this.PuertosMaritimosAdapterRepository = PuertosMaritimosAdapterRepository;
    }


    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
        return  request.bodyToMono(PuertosMaritimos.class).log()
                .flatMap(prod ->PuertosMaritimosAdapterRepository.create(prod)).log()
                .flatMap(prodCreate -> ServerResponse.ok().bodyValue(prodCreate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return  request.bodyToMono(PuertosMaritimos.class).log()
                .flatMap(prod ->PuertosMaritimosAdapterRepository.update(prod))
                .flatMap(prodUpdate -> ServerResponse.status(202).bodyValue(prodUpdate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        return
                PuertosMaritimosAdapterRepository.delete(request.pathVariable("clientId"))
                        .then()
                        .then(ServerResponse.noContent().build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
    }

    @Override
    public Mono<ServerResponse> findPuertosMaritimosById(ServerRequest request) {
        return  PuertosMaritimosAdapterRepository.findPuertosMaritimosById(request.pathVariable("clientId"))
                .flatMap(product ->ServerResponse.ok().bodyValue(product))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> findAllPuertosMaritimos(ServerRequest request) {
        return  PuertosMaritimosAdapterRepository.findAllPuertosMaritimos()
                .collectList()
                .flatMap(allProducts ->ServerResponse.ok().bodyValue(allProducts))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }
}
