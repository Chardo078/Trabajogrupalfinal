package com.programacion.avanzada.modelo;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Data // <--- Magia de Lombok
@NoArgsConstructor // <--- OBLIGATORIO para Jakarta NoSQL
@AllArgsConstructor // <--- Para hacer new Author("1", "Nombre", "Bio")
@Entity
public class Author implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String biography;
}