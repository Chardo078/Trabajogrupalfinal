package com.programacion.avanzada;

import com.programacion.avanzada.modelo.Book;
import com.programacion.avanzada.repository.BookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {
    public static void main(String[] args) {
        System.out.println(">>> INICIANDO SISTEMA (Modo Configuración Separada)...");

        // 1. Inicializador LIMPIO (Sin mapas, sin propiedades hardcodeadas)
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();

        // Obligamos a escanear tus paquetes (Truco para evitar errores de visibilidad)
        initializer.addPackages(true, Main.class);

        try (SeContainer container = initializer.initialize()) {
            // Si llega aquí, es que SmallRye leyó el archivo properties con éxito

            System.out.println(">>> BUSCANDO REPOSITORIO...");
            BookRepository repository = container.select(BookRepository.class).get();

            System.out.println(">>> CREANDO LIBRO...");
            Book libro = new Book("2", "El Señor de los Anillos", "J.R.R. Tolkien", 1954);

            repository.save(libro);
            System.out.println(">>> ¡ÉXITO! Libro guardado usando config externa.");

            // Leemos para confirmar
            repository.findById("2").ifPresent(b ->
                    System.out.println(">>> CONFIRMADO: " + b.getTitle())
            );
        } catch (Exception e) {
            System.err.println(">>> ERROR: No se pudo leer la configuración o conectar a la DB.");
            e.printStackTrace();
        }
    }
}