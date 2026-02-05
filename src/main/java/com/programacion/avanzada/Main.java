package com.programacion.avanzada;

import com.programacion.avanzada.modelo.*;
import com.programacion.avanzada.repository.*;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println(">>> INICIANDO PRUEBA COMPLETA DEL SISTEMA...");

        try (SeContainer container = SeContainerInitializer.newInstance().addPackages(true, Main.class).initialize()) {

            // 1. OBTENER TODOS LOS REPOSITORIOS
            AuthorRepository authorRepo = container.select(AuthorRepository.class).get();
            CustomerRepository customerRepo = container.select(CustomerRepository.class).get();
            InventoryRepository inventoryRepo = container.select(InventoryRepository.class).get();
            PurchaseOrderRepository orderRepo = container.select(PurchaseOrderRepository.class).get();

            System.out.println(">>> REPOSITORIOS CARGADOS. GENERANDO DATOS...");

            // --- A. CREAR UN AUTOR ---
            Author autor = new Author(
                    UUID.randomUUID().toString(), // ID único
                    "Gabriel García Márquez",
                    "Premio Nobel de Literatura, creador de Macondo."
            );
            authorRepo.save(autor);
            System.out.println("✅ Autor guardado: " + autor.getName());

            // --- B. CREAR UN CLIENTE ---
            Customer cliente = new Customer(
                    UUID.randomUUID().toString(),
                    "María Pérez",
                    "maria.perez@email.com"
            );
            customerRepo.save(cliente);
            System.out.println("✅ Cliente guardado: " + cliente.getFullName());

            // --- C. CREAR INVENTARIO ---
            Inventory producto = new Inventory(
                    UUID.randomUUID().toString(),
                    "LAPTOP-GAMER-X1",
                    50, // Stock
                    "Bodega Central - Pasillo 4"
            );
            inventoryRepo.save(producto);
            System.out.println("✅ Inventario actualizado para: " + producto.getProductCode());

            // --- D. LA JOYA DE LA CORONA: ORDEN DE COMPRA CON ITEMS ANIDADOS ---
            // Primero creamos los items (detalle de la compra)
            LineItem item1 = new LineItem(UUID.randomUUID().toString(), "Laptop Gamer X1", 1, 1500.00);
            LineItem item2 = new LineItem(UUID.randomUUID().toString(), "Mouse RGB", 2, 25.50);

            // Creamos la orden vinculada al Cliente y conteniendo la LISTA de items
            PurchaseOrder orden = new PurchaseOrder(
                    UUID.randomUUID().toString(),
                    cliente.getId(), // Relación con el cliente
                    LocalDate.now().toString(),
                    List.of(item1, item2) // <--- ¡AQUÍ ESTÁ LA MAGIA DE NOSQL!
            );

            orderRepo.save(orden);
            System.out.println("✅ Orden de Compra guardada con " + orden.getItems().size() + " items dentro.");

            // --- E. VERIFICACIÓN FINAL (LEER DE LA NUBE) ---
            System.out.println("\n>>> VERIFICANDO DATOS EN MONGODB ATLAS...");

            orderRepo.findById(orden.getId()).ifPresent(o -> {
                System.out.println("🔍 Orden encontrada ID: " + o.getId());
                System.out.println("   Cliente ID: " + o.getCustomerId());
                System.out.println("   Items dentro de la orden:");
                o.getItems().forEach(item ->
                        System.out.println("     - " + item.getProductName() + " ($" + item.getUnitPrice() + ")")
                );
            });

            System.out.println("\n>>> ¡PRUEBA EXITOSA! TODO EL SISTEMA FUNCIONA.");

        } catch (Exception e) {
            System.err.println("❌ ERROR GRAVE:");
            e.printStackTrace();
        }
    }
}