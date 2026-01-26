package com.programacion.avanzada.modelo;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import lombok.Data;      // <--- Importante
import lombok.NoArgsConstructor; // <--- Importante para JNoSQL
import lombok.AllArgsConstructor;

@Data                 // Crea getters, setters, toString, equals, hashcode automático
@NoArgsConstructor    // Crea el constructor vacío ()
@AllArgsConstructor   // Crea el constructor con todo (id, titulo, autor...)
@Entity
public class Book {

    @Id
    private String id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private int year;

    // ¡Y ya no necesitas escribir nada más abajo!
}