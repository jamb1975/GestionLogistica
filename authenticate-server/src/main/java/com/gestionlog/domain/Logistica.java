package com.gestionlog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Logistica {
    private String id;
    private int cantidadProducto;
    private String clienteId;
    private String tipoProductosId;
    private String bodegasId;
    private String puertosMaritimosId;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEntrega;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal precionEnvio;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal valorDescuento;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal valorTotal;
    private String numeroFlota;
    private String placaVehiculo;
    private String numeroGuia;
    private String tipoLogistica;



}
