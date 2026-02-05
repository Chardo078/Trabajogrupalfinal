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
public class LineItem implements Serializable {

    @Id
    private String id;

    @Column
    private String productName;

    @Column
    private int quantity;

    @Column
    private double unitPrice;
}