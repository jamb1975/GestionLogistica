package org.gestionlog.r2dbc.postgresql.gateways;

import org.gestionlog.r2dbc.postgresql.model.Logistica;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LogisticaAdapterRepository {

    public Flux<Logistica> findAllLogistica();
    public Mono<Logistica> create(Logistica Logistica);
    public Mono<Logistica> findLogisticaById(String logisticaId);
    public Mono<Logistica> update(Logistica logistica);
    public Mono<Void> delete(String logisticaId);

}