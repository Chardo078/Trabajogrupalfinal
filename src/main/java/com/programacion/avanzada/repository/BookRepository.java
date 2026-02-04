package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
// IMPORTANTE: Usamos la clase que SÍ aparece en tu imagen
import jakarta.nosql.document.DocumentTemplate;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    @Inject
    private DocumentTemplate template;

    public Book save(Book book) {
        // En la versión b7, se usa insert o update
        return template.insert(book);
    }

    public Optional<Book> findById(String id) {
        return template.find(Book.class, id);
    }

    public void deleteById(String id) {
        template.delete(Book.class, id);
    }
}