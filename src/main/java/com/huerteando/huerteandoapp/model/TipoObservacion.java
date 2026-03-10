package com.huerteando.huerteandoapp.model;

import jakarta.persistence.*;

/*
Tabla: public.tipo_observacion
Tipos fijos: Planta, Rincón, Incidencia. En SQL es smallint, aquí lo mapeo como Short.
*/
@Entity
@Table(name = "tipo_observacion")
public class TipoObservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_observacion")
    private Short id; // PK (smallint)

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre; // texto para mostrar

    public TipoObservacion() {}

    public Short getId() { return id; }
    public void setId(Short id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
