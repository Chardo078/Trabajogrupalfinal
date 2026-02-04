package com.programacion.avanzada;

import com.programacion.avanzada.modelo.Book;
import com.programacion.avanzada.repository.BookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(">>> PREPARANDO CONTENEDOR...");

        // 1. CONFIGURACIÓN "A LA FUERZA" (Mapa de propiedades)
        // Reemplaza con tu conexión real de Atlas
        String mongoUrl = "mongodb+srv://admin:adminp@cluster0.klzmmm6.mongodb.net/biblioteca?appName=Cluster0";

        Map<String, Object> config = new HashMap<>();
        // Ponemos todas las variantes posibles para que JNoSQL no tenga excusa
        config.put("jnosql.mongodb.url", mongoUrl);
        config.put("jnosql.document.database", "biblioteca");
        config.put("jakarta.nosql.document.database", "biblioteca"); // Variante nueva
        config.put("jnosql.mongodb.database", "biblioteca");

        try {
            // 2. INICIALIZADOR CON ESCANEO FORZADO
            SeContainerInitializer initializer = SeContainerInitializer.newInstance();

            // ¡IMPORTANTE! Esto obliga a leer tus clases (Book, Repository)
            initializer.addPackages(true, Main.class);
            initializer.setProperties(config);

            try (SeContainer container = initializer.initialize()) {
                System.out.println(">>> CONTENEDOR INICIADO. BUSCANDO REPOSITORIO...");

                // 3. Intentamos obtener el repositorio
                BookRepository repository = container.select(BookRepository.class).get();

                System.out.println(">>> REPOSITORIO ENCONTRADO. GUARDANDO LIBRO...");

                Book libro = new Book("1", "Harry Potter", "J.K. Rowling", 1997);
                repository.save(libro);

                System.out.println(">>> ¡ÉXITO TOTAL! Libro guardado en MongoDB Atlas.");

                // Verificación
                repository.findById("1").ifPresent(b ->
                        System.out.println(">>> LEÍDO DE LA DB: " + b.getTitle())
                );
            }
        } catch (Exception e) {
            System.err.println(">>> ERROR CRÍTICO:");
            e.printStackTrace();
        }
    }
}