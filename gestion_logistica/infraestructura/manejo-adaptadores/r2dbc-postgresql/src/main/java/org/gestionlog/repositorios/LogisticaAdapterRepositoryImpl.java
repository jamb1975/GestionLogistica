package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.gateways.LogisticaAdapterRepository;
import org.gestionlog.r2dbc.postgresql.model.Logistica;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class LogisticaAdapterRepositoryImpl implements LogisticaAdapterRepository
{
    private final LogisticaRepository logisticaRepository;

    public LogisticaAdapterRepositoryImpl(LogisticaRepository logisticaRepository){
        this.logisticaRepository = logisticaRepository;
    }

    public Mono<Logistica> create(Logistica logistica){
        return Mono.just(UUID.randomUUID())
                .flatMap(uuid -> logisticaRepository.save(logistica) );

    }

    @Override
    public Mono<Logistica> update(Logistica logistica) {
        return logisticaRepository.findLogisticaById(logistica.getId())
                .map(clienteEdit -> clienteEdit.toBuilder().cantidadProducto(logistica.getCantidadProducto()).build()).log()
                .flatMap(prodUppdate -> logisticaRepository.save(prodUppdate)).log();
    }

    public Mono<Void> delete(String logisticaId){
        return logisticaRepository.deleteById(logisticaId);
    }

    @Override
    public Mono<Logistica> findLogisticaById(String logisticaId) {
        return logisticaRepository.findLogisticaById(logisticaId);
    }

    public Flux<Logistica> findAllLogistica(){
        return logisticaRepository.findAll();
    }
}
