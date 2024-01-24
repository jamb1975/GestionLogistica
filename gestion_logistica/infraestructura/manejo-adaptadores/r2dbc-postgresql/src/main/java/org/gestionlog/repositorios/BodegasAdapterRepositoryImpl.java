package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.gateways.BodegasAdapterRepository;
import org.gestionlog.r2dbc.postgresql.model.Bodegas;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public class BodegasAdapterRepositoryImpl implements BodegasAdapterRepository
{
    private final BodegasRepository bodegasRepository;

    public BodegasAdapterRepositoryImpl(BodegasRepository bodegasRepository){
        this.bodegasRepository = bodegasRepository;
    }

    public Mono<Bodegas> create(Bodegas bodegas){
        return Mono.just(UUID.randomUUID())
                .flatMap(uuid -> bodegasRepository.save(bodegas) );

    }

    @Override
    public Mono<Bodegas> update(Bodegas bodegas) {
        return bodegasRepository.findBodegastById(bodegas.getId())
                .map(bodegaEdit -> bodegaEdit.toBuilder().nombre(bodegas.getNombre()).build()).log()
                .flatMap(prodUppdate -> bodegasRepository.save(prodUppdate)).log();
    }

    public Mono<Void> delete(String BodegasId){
        return bodegasRepository.deleteById(BodegasId);
    }

    @Override
    public Mono<Bodegas> findBodegasById(String bodegasId) {
        return bodegasRepository.findBodegastById(bodegasId);
    }

    public Flux<Bodegas> findAllBodegas(){
        return bodegasRepository.findAll();
    }
}
