package com.programacion.avanzada.modelo; // Asegúrate de que el paquete sea correcto

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    private String id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private int year;

    // 1. Constructor vacío (Obligatorio para JNoSQL)
    public Book() {
    }

    // 2. Constructor con todo (Para que funcione tu Main)
    public Book(String id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // 3. Getters y Setters manuales (Porque Lombok falló)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Book{id='" + id + "', title='" + title + "'}";
    }
}