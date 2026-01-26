package com.programacion.avanzada.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MongoDBProducer {

    @Inject
    @ConfigProperty(name = "jnosql.mongodb.database")
    private String database;

    @Inject
    private DocumentCollectionManagerFactory factory;

    @Produces
    public DocumentCollectionManager getManager() {
        return factory.apply(database);
    }
}