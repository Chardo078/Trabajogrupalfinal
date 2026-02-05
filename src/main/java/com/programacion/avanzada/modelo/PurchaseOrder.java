package com.programacion.avanzada.modelo;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseOrder implements Serializable {

    @Id
    private String id;

    @Column
    private String customerId;

    @Column
    private String orderDate;

    @Column
    private List<LineItem> items;
}