package org.gestionlog.repositorios;


import org.gestionlog.r2dbc.postgresql.gateways.ClientesAdapterRepository;
import org.gestionlog.r2dbc.postgresql.model.Clientes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ClientesAdapterRepositoryImpl implements ClientesAdapterRepository
{
    private final ClientesRepository clientesRepository;

    public ClientesAdapterRepositoryImpl(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    public Mono<Clientes> create(Clientes clientes){
       return Mono.just(UUID.randomUUID())
                .flatMap(uuid -> clientesRepository.save(clientes) );

    }

    @Override
    public Mono<Clientes> update(Clientes clientes) {
        return clientesRepository.findClientestById(clientes.getId())
                .map(clienteEdit -> clienteEdit.toBuilder().nombres(clientes.getNombres()).direccion(clientes.getDireccion()).build()).log()
                .flatMap(prodUppdate -> clientesRepository.save(prodUppdate)).log();
    }

    public Mono<Void> delete(String clientesId){
         return clientesRepository.deleteById(clientesId);
    }

    @Override
    public Mono<Clientes> findClientesById(String clientesId) {
        return clientesRepository.findClientestById(clientesId);
    }

    public Flux<Clientes> findAllClientes(){
            return clientesRepository.findAll();
    }
}
