package com.jornada.entity;

public class Caderno {
    private Integer idCaderno;
    private String nomeCaderno;
    private Usuario idUsuario;

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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
