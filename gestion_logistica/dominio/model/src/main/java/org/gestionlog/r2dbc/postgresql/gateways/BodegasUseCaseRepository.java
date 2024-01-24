package org.gestionlog.r2dbc.postgresql.gateways;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface BodegasUseCaseRepository {
    public Mono<ServerResponse> create(ServerRequest request);
    public Mono<ServerResponse> update(ServerRequest request);
    public Mono<ServerResponse> delete(ServerRequest request);
    public Mono<ServerResponse> findBodegasById(ServerRequest request);
    public Mono<ServerResponse> findAllBodegas(ServerRequest request);
}