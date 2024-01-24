package org.gestionlog.config;

import org.gestionlog.r2dbc.postgresql.gateways.*;
import org.gestionlog.repositorios.*;
import org.gestionlog.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class UseCaseConfig {

    @Bean
    @Primary
    public ClientesAdapterRepository clientesAdapterRepository(ClientesRepository clientesRepository){
        return new ClientesAdapterRepositoryImpl(clientesRepository);
    }
    @Bean
    public ClientesUseCaseRepository clientesUseCaseRepository(ClientesAdapterRepository clientesAdapterRepository){
        return new ClientesUseCase(clientesAdapterRepository);
    }

    @Bean
    @Primary
    public TipoProductosAdapterRepository TipoProductosAdapterRepository(TipoProductosRepository tipoProductosRepository){
        return new TipoProductosAdapterRepositoryImpl(tipoProductosRepository);
    }
    @Bean
    public TipoProductosUseCaseRepository TipoProductosUseCaseRepository(TipoProductosAdapterRepository tipoProductosAdapterRepository){
        return new TipoProductosUseCase(tipoProductosAdapterRepository);
    }
    @Bean
    @Primary
    public BodegasAdapterRepository bodegasAdapterRepository(BodegasRepository bodegasRepository){
        return new BodegasAdapterRepositoryImpl(bodegasRepository);
    }
    @Bean
    public BodegasUseCaseRepository bodegasUseCaseRepository(BodegasAdapterRepository bodegasAdapterRepository){
        return new BodegasUseCase(bodegasAdapterRepository);
    }

    @Bean
    @Primary
    public PuertosMaritimosAdapterRepository puertosMaritimosAdapterRepository(PuertosMaritimosRepository puertosMaritimosRepository){
        return new PuertosMaritimosAdapterRepositoryImpl(puertosMaritimosRepository);
    }
    @Bean
    public PuertosMaritimosUseCaseRepository puertosMaritimosUseCaseRepository(PuertosMaritimosAdapterRepository puertosMaritimosAdapterRepository){
        return new PuertosMaritimosUseCase(puertosMaritimosAdapterRepository);
    }
    @Bean
    @Primary
    public LogisticaAdapterRepository logisticaAdapterRepository(LogisticaRepository logisticaRepository){
        return new LogisticaAdapterRepositoryImpl(logisticaRepository);
    }
    @Bean
    public LogisticaUseCaseRepository logisticaUseCaseRepository(LogisticaAdapterRepository logisticaAdapterRepository){
        return new LogisticaUseCase(logisticaAdapterRepository);
    }
    


}
