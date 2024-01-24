package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.gateways.PuertosMaritimosAdapterRepository;
import org.gestionlog.r2dbc.postgresql.model.PuertosMaritimos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class PuertosMaritimosAdapterRepositoryImpl implements PuertosMaritimosAdapterRepository
{
    private final PuertosMaritimosRepository puertosMaritimosRepository;

    public PuertosMaritimosAdapterRepositoryImpl(PuertosMaritimosRepository puertosMaritimosRepository){
        this.puertosMaritimosRepository = puertosMaritimosRepository;
    }

    public Mono<PuertosMaritimos> create(PuertosMaritimos puertosMaritimos){
        return Mono.just(UUID.randomUUID())
                .flatMap(uuid -> puertosMaritimosRepository.save(puertosMaritimos) );

    }

    @Override
    public Mono<PuertosMaritimos> update(PuertosMaritimos puertosMaritimos) {
        return puertosMaritimosRepository.findPuertosMaritimosById(puertosMaritimos.getId())
                .map(clienteEdit -> clienteEdit.toBuilder().nombre(puertosMaritimos.getNombre()).build()).log()
                .flatMap(prodUppdate -> puertosMaritimosRepository.save(prodUppdate)).log();
    }

    public Mono<Void> delete(String puertosMaritimosId){
        return puertosMaritimosRepository.deleteById(puertosMaritimosId);
    }

    @Override
    public Mono<PuertosMaritimos> findPuertosMaritimosById(String puertosMaritimosId) {
        return puertosMaritimosRepository.findPuertosMaritimosById(puertosMaritimosId);
    }

    public Flux<PuertosMaritimos> findAllPuertosMaritimos(){
        return puertosMaritimosRepository.findAll();
    }
}
