package com.huerteando.huerteandoapp.model;

import jakarta.persistence.*;

/*
Tabla: public.catalogo_eei
Cada fila es una especie del Catálogo Español de Especies Exóticas Invasoras.
Una especie (Especie) puede contrastarse con esta tabla para determinar si es invasora.
*/
@Entity
@Table(name = "catalogo_eei")
public class CatalogoEei {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catalogo_eei")
    private Long id; // PK

    @Column(name = "nombre_cientifico", nullable = false, unique = true)
    private String nombreCientifico; // clave natural del catálogo

    @Column(name = "nombre_comun")
    private String nombreComun;

    @Column(name = "reino")
    private String reino;

    @Column(name = "familia")
    private String familia;

    @Column(name = "normativa_ref")
    private String normativaRef; // referencia normativa (ej. Real Decreto 630/2013)

    public CatalogoEei() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCientifico() { return nombreCientifico; }
    public void setNombreCientifico(String nombreCientifico) { this.nombreCientifico = nombreCientifico; }

    public String getNombreComun() { return nombreComun; }
    public void setNombreComun(String nombreComun) { this.nombreComun = nombreComun; }

    public String getReino() { return reino; }
    public void setReino(String reino) { this.reino = reino; }

    public String getFamilia() { return familia; }
    public void setFamilia(String familia) { this.familia = familia; }

    public String getNormativaRef() { return normativaRef; }
    public void setNormativaRef(String normativaRef) { this.normativaRef = normativaRef; }
}
