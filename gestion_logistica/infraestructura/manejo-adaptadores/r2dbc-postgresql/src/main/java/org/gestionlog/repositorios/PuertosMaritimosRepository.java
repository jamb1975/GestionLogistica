package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.model.Clientes;
import org.gestionlog.r2dbc.postgresql.model.PuertosMaritimos;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PuertosMaritimosRepository extends ReactiveCrudRepository<PuertosMaritimos, String> {
    @Query("SELECT * FROM PuertosMaritimos WHERE  ID = :pmId")
    public Mono<PuertosMaritimos> findPuertosMaritimosById(String pmId);
}
