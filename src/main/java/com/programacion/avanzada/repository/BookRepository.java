package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    @Inject
    private DocumentTemplate template;

    // --- C: CREATE ---
    public Book save(Book book) {
        // 'insert' falla si el ID ya existe en la base de datos
        return template.insert(book);
    }

    // --- U: UPDATE (¡Este te faltaba!) ---
    public Book update(Book book) {
        // 'update' busca el ID y reemplaza los datos. Falla si el ID no existe.
        return template.update(book);
    }

    // --- R: READ (Uno solo) ---
    public Optional<Book> findById(String id) {
        return template.find(Book.class, id);
    }

    // --- R: READ (Listar todos - Muy útil para el frontend) ---
    public List<Book> findAll() {
        // Esto equivale a un "SELECT * FROM Book"
        return template.select(Book.class).result();
    }

    // --- D: DELETE ---
    public void deleteById(String id) {
        template.delete(Book.class, id);
    }
}