package com.programacion.avanzada.modelo;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data // Genera Getters, Setters, toString, hashCode, equals
@NoArgsConstructor // Constructor vacío (Obligatorio para NoSQL)
@AllArgsConstructor // Constructor con todos los argumentos
@Entity
public class Customer implements Serializable {

    @Id
    private String id;

    @Column
    private String fullName;

    @Column
    private String email;
}