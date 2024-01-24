package org.gestionlog.r2dbc.postgresql.gateways;

import org.gestionlog.r2dbc.postgresql.model.Bodegas;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BodegasAdapterRepository {


    public Flux<Bodegas> findAllBodegas();
    public Mono<Bodegas> create(Bodegas bodega);
    public Mono<Bodegas> findBodegasById(String bodegaId);
    public Mono<Bodegas> update(Bodegas bodega);
    public Mono<Void> delete(String bodegaId);

}