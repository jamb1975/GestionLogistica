package org.gestionlog.r2dbc.postgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@Table(name = "bodegas")
public class Bodegas {
    @Id
    private String id;
    private String nombre;
    private String ubicacion;

}
