package com.jornada.entity;

public class Caderno {
    private Integer idCaderno;
    private String nomeCaderno;
    private Usuario id_usuario;

    public Integer getIdCaderno() {
        return idCaderno;
    }

    public void setIdCaderno(Integer idCaderno) {
        this.idCaderno = idCaderno;
    }

    public String getNomeCaderno() {
        return nomeCaderno;
    }

    public void setNomeCaderno(String nomeCaderno) {
        this.nomeCaderno = nomeCaderno;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
            this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "idCaderno=" + idCaderno +
                ", nomeCaderno='" + nomeCaderno + '\'' +
                ", idUsuario=" + id_usuario +
                '}';
    }
}
