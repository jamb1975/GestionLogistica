package com.gestionlog.repository;

import com.gestionlog.domain.UserAccount;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserAccountRepository extends ReactiveCrudRepository<UserAccount, String> {
	
	
	@Query("SELECT u.* FROM public.user_account ")
	Flux<UserAccount> allUsers(String id_session);
    
}
