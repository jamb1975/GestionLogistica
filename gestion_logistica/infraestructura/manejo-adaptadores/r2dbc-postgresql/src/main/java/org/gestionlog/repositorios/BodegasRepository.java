package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.model.Bodegas;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface BodegasRepository extends ReactiveCrudRepository<Bodegas, String> {
    @Query("SELECT * FROM Bodegas WHERE  ID = :bodegaId")
    public Mono<Bodegas> findBodegastById(String bodegaId);
}