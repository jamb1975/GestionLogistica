package com.gestionlog;

import com.gestionlog.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class AuthenticateServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateServerApplication.class, args);
	}
	
	@EnableWebFluxSecurity
	public class AuthWebfluxSecurityConfig {
	@Autowired
	private UserAccountRepository userAccountRepository;
		
		 @Bean
		 public MapReactiveUserDetailsService userDetailsService() {
		
			
			UserDetails user = User
				      .withUsername("security")
				      .password(passwordEncoder().encode("adminsecurity123*"))
				      .roles("USER")
				       
				      .build();
				    return new MapReactiveUserDetailsService(user);
		}
		 
		 @Bean
		    public PasswordEncoder passwordEncoder() {
			  return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		    }

			@Bean
			public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
				http
					.authorizeExchange(exchanges -> exchanges
					    .anyExchange().authenticated()
					)
					.httpBasic()
					;
				return http.csrf().disable().build();
			}
	}

	@Bean
	WebClient webClient(ReactiveClientRegistrationRepository clientRegistration) {

		var clientService = new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistration);
		var authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistration, clientService);

		var oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		oauth.setDefaultClientRegistrationId("7051584c-2777-4b97-a09c-dafade79c43d");

		return WebClient.builder()
				.filter(oauth)
				.build();
	}

	@Bean
	ReactiveClientRegistrationRepository clientRegistration() {

		ClientRegistration registration = ClientRegistration
				.withRegistrationId("7051584c-2777-4b97-a09c-dafade79c43d")
				.tokenUri("http://localhost:9000/oauth2/token")
				.clientId("gestionlog")
				.clientSecret("gestionlog")
				.authorizationGrantType(new AuthorizationGrantType("client_credentials"))
				.build();

		return new InMemoryReactiveClientRegistrationRepository(registration);
	}
}
