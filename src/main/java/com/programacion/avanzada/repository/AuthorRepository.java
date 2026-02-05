package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuthorRepository {

    @Inject
    private DocumentTemplate template;

    public Author save(Author author) {
        return template.insert(author);
    }

    public Author update(Author author) {
        return template.update(author);
    }

    public void deleteById(String id) {
        template.delete(Author.class, id);
    }

    public Optional<Author> findById(String id) {
        return template.find(Author.class, id);
    }

    public List<Author> findAll() {
        return template.select(Author.class).result();
    }
}