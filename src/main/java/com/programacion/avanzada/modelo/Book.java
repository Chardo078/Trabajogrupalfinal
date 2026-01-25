package com.programacion.avanzada.modelo;


import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity

public class Book {

    @Id
    private String id;

    @Column
    private String nombre;
}
