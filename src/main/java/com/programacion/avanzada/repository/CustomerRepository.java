package com.programacion.avanzada.repository;

import com.programacion.avanzada.modelo.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentTemplate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    private DocumentTemplate template;

    public Customer save(Customer customer) {
        return template.insert(customer);
    }

    public Customer update(Customer customer) {
        return template.update(customer);
    }

    public void deleteById(String id) {
        template.delete(Customer.class, id);
    }

    public Optional<Customer> findById(String id) {
        return template.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return template.select(Customer.class).result();
    }
}