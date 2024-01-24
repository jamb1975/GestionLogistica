package com.gestionlog.servicios;

import com.gestionlog.domain.Clientes;
import com.gestionlog.dto.DatosOauth2Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class ClientesServicios {


    @Autowired
    private WebClient client;


    public Mono<ServerResponse> allClientes(ServerRequest request) {

		return client.get().uri("http://localhost:8098/clientes").retrieve().toEntity(Object.class).log()
				.flatMap(entity -> ServerResponse.status(entity.getStatusCode()).bodyValue(entity.getBody()))
				.onErrorResume(error ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    public Mono<ServerResponse> saveCliente(ServerRequest request) {
    return   request.bodyToMono(Clientes.class).log()
            .flatMap(req -> client.post().uri("http://localhost:8098/clientes")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .retrieve().bodyToMono(Clientes.class).log()
                .flatMap(entity -> ServerResponse.status(HttpStatus.OK).bodyValue(entity))
                .onErrorResume(error ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
