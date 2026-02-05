package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.PurchaseOrder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PurchaseOrderRepository {

    @Inject
    private DocumentTemplate template;

    public PurchaseOrder save(PurchaseOrder order) {
        return template.insert(order);
    }

    public PurchaseOrder update(PurchaseOrder order) {
        return template.update(order);
    }

    public void deleteById(String id) {
        template.delete(PurchaseOrder.class, id);
    }

    public Optional<PurchaseOrder> findById(String id) {
        return template.find(PurchaseOrder.class, id);
    }

    public List<PurchaseOrder> findAll() {
        return template.select(PurchaseOrder.class).result();
    }
}