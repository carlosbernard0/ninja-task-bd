package com.jornada.service;

import com.jornada.entity.Usuario;
import com.jornada.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }
    public Usuario salvarUsuario(Usuario usuario) throws Exception{
        if (!usuario.getEmail_usuario().contains("@gmail")){
            throw new Exception("Precisa ser @gmail");
        }
        Usuario usuarioSalvo = usuarioRepository.salvarUsuarioDB(usuario);
        System.out.println("\n");
        return usuarioSalvo;
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = usuarioRepository.listarUsuario();
        lista.stream().forEach(System.out::println);
        return lista;
    }
}
