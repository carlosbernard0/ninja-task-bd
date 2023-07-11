package com.jornada.service;

import com.jornada.entity.Usuario;

public class UsuarioService {
    public Usuario salvarCliente(Usuario usuario) throws Exception{
        if (!usuario.getEmail_usuario().contains("@gmail")){
            throw new Exception("Precisa ser @gmail");
        }
    }
}
