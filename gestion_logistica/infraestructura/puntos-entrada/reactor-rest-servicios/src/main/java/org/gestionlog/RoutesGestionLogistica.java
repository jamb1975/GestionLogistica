package org.gestionlog;

import org.gestionlog.r2dbc.postgresql.gateways.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
//@EnableWebFlux
public class RoutesGestionLogistica {

    @Bean
    public RouterFunction<ServerResponse> routes(ClientesUseCaseRepository useCaseRepository) {

        return route(POST("/clientes"), useCaseRepository::create)
                .andRoute(DELETE("/clientes/{clientesId}"), useCaseRepository::delete)
                .andRoute(GET("/clientes/{clientesId}"), useCaseRepository::findClientesById)
                .andRoute(PUT("/clientes"), useCaseRepository::update)
                .andRoute(GET("/clientes"), useCaseRepository::findAllClientes);

    }

    @Bean
    public RouterFunction<ServerResponse> routesTipoProductos(TipoProductosUseCaseRepository useCaseRepository) {

        return route(POST("/tipoProductos"), useCaseRepository::create)
                .andRoute(DELETE("/tipoProductos/{tipoProductosId}"), useCaseRepository::delete)
                .andRoute(GET("/tipoProductos/{tipoProductosId}"), useCaseRepository::findTipoProductosById)
                .andRoute(PUT("/tipoProductos"), useCaseRepository::update)
                .andRoute(GET("/tipoProductos"), useCaseRepository::findAllTipoProductos);

    }
    @Bean
    public RouterFunction<ServerResponse> routesBodegas(BodegasUseCaseRepository useCaseRepository) {

        return route(POST("/bodegas"), useCaseRepository::create)
                .andRoute(DELETE("/bodegas/{bodegasId}"), useCaseRepository::delete)
                .andRoute(GET("/bodegas/{bodegasId}"), useCaseRepository::findBodegasById)
                .andRoute(PUT("/bodegas"), useCaseRepository::update)
                .andRoute(GET("/bodegas"), useCaseRepository::findAllBodegas);

    }
    @Bean
    public RouterFunction<ServerResponse> routesPuertosMaritimos(PuertosMaritimosUseCaseRepository useCaseRepository) {

        return route(POST("/puertosMaritimos"), useCaseRepository::create)
                .andRoute(DELETE("/puertosMaritimos/{puertosMaritimosId}"), useCaseRepository::delete)
                .andRoute(GET("/puertosMaritimos/{puertosMaritimosId}"), useCaseRepository::findPuertosMaritimosById)
                .andRoute(PUT("/puertosMaritimos"), useCaseRepository::update)
                .andRoute(GET("/puertosMaritimos"), useCaseRepository::findAllPuertosMaritimos);

    }
    @Bean
    public RouterFunction<ServerResponse> routesLogistica(LogisticaUseCaseRepository useCaseRepository) {

        return route(POST("/logistica"), useCaseRepository::create)
                .andRoute(DELETE("/logistica/{logisticaId}"), useCaseRepository::delete)
                .andRoute(GET("/logistica/{logisticaId}"), useCaseRepository::findLogisticaById)
                .andRoute(PUT("/logistica"), useCaseRepository::update)
                .andRoute(GET("/logistica"), useCaseRepository::findAllLogistica);

    }
}
