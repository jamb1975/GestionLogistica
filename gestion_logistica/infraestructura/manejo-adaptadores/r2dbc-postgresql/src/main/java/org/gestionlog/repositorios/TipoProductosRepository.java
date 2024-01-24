package org.gestionlog.repositorios;

import org.gestionlog.r2dbc.postgresql.model.Clientes;
import org.gestionlog.r2dbc.postgresql.model.TipoProductos;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TipoProductosRepository extends ReactiveCrudRepository<TipoProductos, String> {
    @Query("SELECT * FROM TipoProductos WHERE  ID = :clientId")
    public Mono<TipoProductos> findTipoProductosById(String tipoProductoId);
}