package org.gestionlog.r2dbc.postgresql.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@Table(name = "clientes")
public class Clientes {
    @Id
    @Column(value = "ID")
    private String id;
    @Column(value = "NOMBRES")
    private String nombres;
    @Column(value = "NO_IDENTIFICACION")
    private String no_identificacion;
    @Column(value = "NO_TELEFONO")
    private String no_telefono;
    @Column(value = "EMAIL")
    private String email;
    @Column(value = "DIRECCION")
    private String direccion;

}
