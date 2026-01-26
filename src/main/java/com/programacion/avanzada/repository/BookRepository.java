package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.Book;
import jakarta.data.repository.Repository;
import java.util.List;
import java.util.Optional;

// "String" es el tipo de dato del @Id
public interface BookRepository extends Repository<Book, String> {

    // Método personalizado (se crea automático por el nombre)
    List<Book> findByAuthor(String author);

    // Los métodos save, deleteById, findById, findAll ya vienen incluidos.
}