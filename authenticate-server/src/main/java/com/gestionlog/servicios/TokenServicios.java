package com.gestionlog.servicios;

import com.gestionlog.dto.DatosOauth2Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TokenServicios {


	@Autowired
	private WebClient client;


	public Mono<ServerResponse> getToken(ServerRequest request) {
       return ServerResponse.ok().bodyValue(true);
		/*return client.get().uri("http://localhost:8098/products").retrieve().toEntity(Object.class).log()
				.flatMap(entity -> ServerResponse.status(entity.getStatusCode()).bodyValue(entity.))
				.onErrorResume(error ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());*/
	}
}
