package org.gestionlog.r2dbc.postgresql.gateways;

import org.gestionlog.r2dbc.postgresql.model.PuertosMaritimos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PuertosMaritimosAdapterRepository {


    public Flux<PuertosMaritimos> findAllPuertosMaritimos();
    public Mono<PuertosMaritimos> create(PuertosMaritimos PuertosMaritimos);
    public Mono<PuertosMaritimos> findPuertosMaritimosById(String puertosMaritimosId);
    public Mono<PuertosMaritimos> update(PuertosMaritimos puertosMaritimos);
    public Mono<Void> delete(String puertosMaritimosId);

}