package org.gestionlog.usecase;

import org.gestionlog.r2dbc.postgresql.gateways.ClientesAdapterRepository;
import org.gestionlog.r2dbc.postgresql.gateways.ClientesUseCaseRepository;
import org.gestionlog.r2dbc.postgresql.model.Clientes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ClientesUseCase implements ClientesUseCaseRepository {

    private final ClientesAdapterRepository clientesAdapterRepository;

    public ClientesUseCase(ClientesAdapterRepository clientesAdapterRepository){
        this.clientesAdapterRepository = clientesAdapterRepository;
    }


    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
      return  request.bodyToMono(Clientes.class).log()
                .flatMap(prod ->clientesAdapterRepository.create(prod)).log()
              .flatMap(prodCreate -> ServerResponse.ok().bodyValue(prodCreate))
              .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return  request.bodyToMono(Clientes.class).log()
                .flatMap(prod ->clientesAdapterRepository.update(prod))
                .flatMap(prodUpdate -> ServerResponse.status(202).bodyValue(prodUpdate))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
     return
             clientesAdapterRepository.delete(request.pathVariable("clientId"))
                .then()
                .then(ServerResponse.noContent().build())
                     .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
    }

    @Override
    public Mono<ServerResponse> findClientesById(ServerRequest request) {
      return  clientesAdapterRepository.findClientesById(request.pathVariable("clientId"))
                .flatMap(product ->ServerResponse.ok().bodyValue(product))
              .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }

    @Override
    public Mono<ServerResponse> findAllClientes(ServerRequest request) {
        return  clientesAdapterRepository.findAllClientes()
                .collectList()
                .flatMap(allProducts ->ServerResponse.ok().bodyValue(allProducts))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));

    }
}
