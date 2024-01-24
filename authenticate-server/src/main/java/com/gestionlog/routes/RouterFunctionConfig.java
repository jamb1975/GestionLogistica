package com.gestionlog.routes;

import com.gestionlog.servicios.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
public class RouterFunctionConfig {
	
	@Bean
	public RouterFunction<ServerResponse> routes(TokenServicios tokenServicios) {
		 
		return route(GET("/api/token"), tokenServicios::getToken);

				
	}

	@Bean
	public RouterFunction<ServerResponse> routesClientes(ClientesServicios clientesServicios) {

		return route(GET("/api/clientes"), clientesServicios::allClientes)
				.andRoute(POST("/api/clientes"), clientesServicios::saveCliente);
	}
	@Bean
	public RouterFunction<ServerResponse> routesTipoProductos(TipoProductosServicios tipoProductosServicios) {

		return route(GET("/api/tipoProductos"), tipoProductosServicios::allTipoProductos)
				.andRoute(POST("/api/tipoProductos"), tipoProductosServicios::saveCliente);
	}

	@Bean
	public RouterFunction<ServerResponse> routesBodegas(BodegasServicios bodegasServicios) {

		return route(GET("/api/bodegas"), bodegasServicios::allBodegas)
				.andRoute(POST("/api/bodegas"), bodegasServicios::saveCliente);
	}
	@Bean
	public RouterFunction<ServerResponse> routesPuertosMaritimos(PuertosMaritimosServicios puertosMaritimosServicios) {

		return route(GET("/api/puertosMaritimos"), puertosMaritimosServicios::allPuertosMaritimos)
				.andRoute(POST("/api/puertosMaritimos"), puertosMaritimosServicios::saveCliente);
	}
	@Bean
	public RouterFunction<ServerResponse> routesLogistica(LogisticaServicios logisticaServicios) {

		return route(GET("/api/logistica"), logisticaServicios::allLogistica)
				.andRoute(POST("/api/logistica"), logisticaServicios::saveCliente);
	}

}
