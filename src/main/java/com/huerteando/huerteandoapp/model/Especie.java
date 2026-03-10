package com.huerteando.huerteandoapp.model;

import jakarta.persistence.*;

/*
Tabla: public.especie
Catálogo de especies observables. Si tiene id_catalogo_eei es que está en el catálogo de invasoras.
*/
@Entity
@Table(name = "especie")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especie")
    private Long id; // PK

    @Column(name = "nombre_cientifico", nullable = false, unique = true)
    private String nombreCientifico; // clave natural útil

    @Column(name = "nombre_comun")
    private String nombreComun;

    @Column(name = "familia")
    private String familia;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_catalogo_eei")
    private CatalogoEei catalogoEei; // entrada en el catálogo EEI si es invasora (nullable)

    public Especie() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCientifico() { return nombreCientifico; }
    public void setNombreCientifico(String nombreCientifico) { this.nombreCientifico = nombreCientifico; }

    public String getNombreComun() { return nombreComun; }
    public void setNombreComun(String nombreComun) { this.nombreComun = nombreComun; }

    public String getFamilia() { return familia; }
    public void setFamilia(String familia) { this.familia = familia; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public CatalogoEei getCatalogoEei() { return catalogoEei; }
    public void setCatalogoEei(CatalogoEei catalogoEei) { this.catalogoEei = catalogoEei; }
}
