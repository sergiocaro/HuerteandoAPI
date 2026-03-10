package com.huerteando.huerteandoapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
Tabla: public.usuario
Usuario básico. Aquí no hay magia: datos + flags + fechas.
*/
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id; // PK

    @Column(name = "nombre", nullable = false)
    private String nombre; // obligatorio

    @Column(name = "apellidos")
    private String apellidos; // opcional

    @Column(name = "nick", nullable = false, unique = true)
    private String nick; // único y obligatorio

    @Column(name = "email", unique = true)
    private String email; // opcional pero único si lo usas

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; // aquí NO guardas la password en claro

    @Column(name = "avatar_url")
    private String avatarUrl; // url del avatar si la tienes

    @Column(name = "rol", nullable = false)
    private String rol; // 'USER' por defecto en SQL

    @Column(name = "activo", nullable = false)
    private Boolean activo; // default true en SQL

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro; // default now() en SQL

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso; // opcional

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(LocalDateTime ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }
}
