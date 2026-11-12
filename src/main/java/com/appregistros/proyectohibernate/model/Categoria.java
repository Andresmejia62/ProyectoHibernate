package com.appregistros.proyectohibernate.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    @Entity
    @Table(name = "categoria", schema = "catalogos")
    public class categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
(name = "nombre", nullable = false, length = 100)
        private String nombre;
        @Column(name = "descripcion", nullable = false, length = 250)
        private String descripcion;

        @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
        private List<Libro> libros = new ArrayList<>();


    }
}
