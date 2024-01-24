package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.model.Logistica;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LogisticaRepository extends ReactiveCrudRepository<Logistica, String> {
    @Query("SELECT * FROM Logistica WHERE  ID = :logisticaId")
    public Mono<Logistica> findLogisticaById(String logisticaId);
}