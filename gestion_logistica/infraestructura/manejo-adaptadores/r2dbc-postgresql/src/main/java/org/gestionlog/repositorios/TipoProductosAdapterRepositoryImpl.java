package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.gateways.TipoProductosAdapterRepository;
import org.gestionlog.r2dbc.postgresql.model.TipoProductos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class TipoProductosAdapterRepositoryImpl implements TipoProductosAdapterRepository
{
    private final TipoProductosRepository tipoProductosRepository;

    public TipoProductosAdapterRepositoryImpl(TipoProductosRepository tipoProductosRepository){
        this.tipoProductosRepository = tipoProductosRepository;
    }

    public Mono<TipoProductos> create(TipoProductos TipoProductos){
        return Mono.just(UUID.randomUUID())
                .flatMap(uuid -> tipoProductosRepository.save(TipoProductos) );

    }

    @Override
    public Mono<TipoProductos> update(TipoProductos tipoProductos) {
        return tipoProductosRepository.findTipoProductosById(tipoProductos.getId())
                .map(clienteEdit -> clienteEdit.toBuilder().tipo(tipoProductos.getTipo()).build()).log()
                .flatMap(prodUppdate -> tipoProductosRepository.save(prodUppdate)).log();
    }

    public Mono<Void> delete(String tipoProductosId){
        return tipoProductosRepository.deleteById(tipoProductosId);
    }

    @Override
    public Mono<TipoProductos> findTipoProductosById(String tipoProductosId) {
        return tipoProductosRepository.findTipoProductosById(tipoProductosId);
    }

    public Flux<TipoProductos> findAllTipoProductos(){
        return tipoProductosRepository.findAll();
    }
}