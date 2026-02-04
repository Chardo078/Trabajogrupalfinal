package com.programacion.avanzada;

import com.programacion.avanzada.modelo.Book;
import com.programacion.avanzada.repository.BookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        // 1. Iniciamos el contenedor CDI (Weld)
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            // 2. Obtenemos el repositorio (Inyección manual)
            BookRepository repository = container.select(BookRepository.class).get();

            // --- CREATE (Crear) ---
            String id = UUID.randomUUID().toString();
            Book libro = new Book(id, "El Señor de los Anillos", "J.R.R. Tolkien", 1954);
            repository.save(libro);
            System.out.println("Libro guardado: " + libro);

            // --- READ (Leer) ---
            Book libroEncontrado = repository.findById(id).orElseThrow();
            System.out.println("Libro encontrado en DB: " + libroEncontrado.getTitle());

            // --- UPDATE (Actualizar) ---
            libroEncontrado.setTitle("El Señor de los Anillos: La Comunidad del Anillo");
            repository.save(libroEncontrado); // .save() también actualiza si el ID ya existe
            System.out.println("Libro actualizado.");

            // --- DELETE (Borrar) ---
            repository.deleteById(id);
            System.out.println("Libro eliminado.");
        }
    }
}