package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.model.Clientes;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ClientesRepository extends ReactiveCrudRepository<Clientes, String> {
    @Query("SELECT * FROM Clientes WHERE  ID = :clientId")
    public Mono<Clientes> findClientestById(String clientId);
}
