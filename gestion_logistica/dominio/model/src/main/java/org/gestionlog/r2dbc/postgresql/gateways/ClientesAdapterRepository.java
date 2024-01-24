package org.gestionlog.r2dbc.postgresql.gateways;


import org.gestionlog.r2dbc.postgresql.model.Clientes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientesAdapterRepository {


    public Flux<Clientes> findAllClientes();
    public Mono<Clientes> create(Clientes cliente);
    public Mono<Clientes> findClientesById(String clienteId);
    public Mono<Clientes> update(Clientes cliente);
    public Mono<Void> delete(String clienteId);

}
