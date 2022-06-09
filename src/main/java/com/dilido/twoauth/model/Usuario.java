package com.dilido.twoauth.model;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String email;
    private String password;
    private String estado;
    private String trabajador;
    private String idEntidad;
    private String created_at;
    private String updated_at;
    private String factauth;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(String trabajador) {
        this.trabajador = trabajador;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getFactauth() {
        return factauth;
    }

    public void setFactauth(String factauth) {
        this.factauth = factauth;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"idEntidad\":"+idEntidad+",\n" +
                "    \"email\":\""+email+"\",\n" +
                "    \"nombre\":\""+nombre+"\",\n" +
                "    \"password\":\""+password+"\",\n" +
                "    \"estado\":\"1\"\n" +
                "}";
    }
}
