package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.Inventory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InventoryRepository {

    @Inject
    private DocumentTemplate template;

    public Inventory save(Inventory inventory) {
        return template.insert(inventory);
    }

    public Inventory update(Inventory inventory) {
        return template.update(inventory);
    }

    public void deleteById(String id) {
        template.delete(Inventory.class, id);
    }

    public Optional<Inventory> findById(String id) {
        return template.find(Inventory.class, id);
    }

    public List<Inventory> findAll() {
        return template.select(Inventory.class).result();
    }
}