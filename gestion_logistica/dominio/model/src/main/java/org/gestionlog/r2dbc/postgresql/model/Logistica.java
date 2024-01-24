package org.gestionlog.r2dbc.postgresql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@Table(name = "logistica")
public class Logistica {
    @Id
    private String id;
    private int cantidadProducto;
    private UUID clienteId;
    private UUID tipoProductosId;
    private UUID bodegasId;
    private UUID puertosMaritimosId;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEntrega;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal precioEnvio;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal valorDescuento;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal valorTotal;
    private String numeroFlota;
    private String placaVehiculo;
    private String numeroGuia;
    private String tipoLogistica;



}
