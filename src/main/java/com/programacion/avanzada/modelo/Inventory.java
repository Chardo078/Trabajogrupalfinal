package com.programacion.avanzada.modelo;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory implements Serializable {

    @Id
    private String id;

    @Column
    private String productCode;

    @Column
    private int stockQuantity;

    @Column
    private String location; // Ej: "Bodega A", "Estante 3"
}