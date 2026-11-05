package com.appregistros.proyectohibernate.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }

    public static synchronized void iniciar() {
        if (entityManagerFactory == null) {
            Map<String, Object> properties = new HashMap<>();
            properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("jakarta.persistence.jdbc.url", getEnvOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/bdbiblioteca"));
            properties.put("jakarta.persistence.jdbc.user", getEnvOrDefault("DB_USER", "andresmejia"));
            properties.put("jakarta.persistence.jdbc.password", getEnvOrDefault("DB_PASSWORD", "amejia62"));
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.show_sql", "false");

            entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecaPU", properties);
        }
    }

    public static EntityManager getEntityManager() {
        iniciar();
        return entityManagerFactory.createEntityManager();
    }

    public static synchronized void cerrar() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}
