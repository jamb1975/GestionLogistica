package com.gestionlog.servicios;

import com.gestionlog.domain.Logistica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Service
public class LogisticaServicios {


    @Autowired
    private WebClient client;


    public Mono<ServerResponse> allLogistica(ServerRequest request) {

        return client.get().uri("http://localhost:8098/logistica").retrieve().toEntity(Object.class).log()
                .flatMap(entity -> ServerResponse.status(entity.getStatusCode()).bodyValue(entity.getBody()))
                .onErrorResume(error ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    public Mono<ServerResponse> saveCliente(ServerRequest request) {
        return   request.bodyToMono(Logistica.class).log()
                .flatMap(req -> client.post().uri("http://localhost:8098/logistica")
                        .accept(MediaType.APPLICATION_JSON)
                        .bodyValue(req)
                        .retrieve().bodyToMono(Logistica.class).log()
                        .flatMap(entity -> ServerResponse.status(HttpStatus.OK).bodyValue(entity))
                        .onErrorResume(error ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
