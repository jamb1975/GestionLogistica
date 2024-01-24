package org.gestionlog.r2dbc.postgresql.gateways;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface ClientesUseCaseRepository {
    public Mono<ServerResponse> create(ServerRequest request);
    public Mono<ServerResponse> update(ServerRequest request);
    public Mono<ServerResponse> delete(ServerRequest request);
    public Mono<ServerResponse> findClientesById(ServerRequest request);
    public Mono<ServerResponse> findAllClientes(ServerRequest request);
}
