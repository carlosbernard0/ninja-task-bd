package com.jornada.service;

import com.jornada.entity.Usuario;
import com.jornada.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }
    public Usuario salvarCliente(Usuario usuario) throws Exception{
        if (!usuario.getEmail_usuario().contains("@gmail")){
            throw new Exception("Precisa ser @gmail");
        }
        Usuario usuarioSalvo = usuarioRepository.salvarUsuarioDB(usuario);
        return usuarioSalvo;
    }
}
