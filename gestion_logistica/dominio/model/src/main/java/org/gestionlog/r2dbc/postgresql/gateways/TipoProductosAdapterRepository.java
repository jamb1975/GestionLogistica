package org.gestionlog.r2dbc.postgresql.gateways;

import org.gestionlog.r2dbc.postgresql.model.TipoProductos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoProductosAdapterRepository {


    public Flux<TipoProductos> findAllTipoProductos();
    public Mono<TipoProductos> create(TipoProductos tipoProductos);
    public Mono<TipoProductos> findTipoProductosById(String tipoProductosId);
    public Mono<TipoProductos> update(TipoProductos tipoProductos);
    public Mono<Void> delete(String tipoProductosId);

}