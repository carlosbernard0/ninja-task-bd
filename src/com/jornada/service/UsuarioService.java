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
        validarUsuario(usuario);

        Usuario usuarioSalvo = usuarioRepository.salvarUsuarioDB(usuario);
        System.out.println("\n");
        return usuarioSalvo;
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = usuarioRepository.listarUsuario();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    public boolean editarUsuario(Usuario usuario) throws Exception {
        validarUsuario(usuario);
        return usuarioRepository.editarUsuario(usuario);
    }

    public void validarUsuario(Usuario usuario) throws Exception {
        if (!usuario.getEmail_usuario().contains("@")){
            throw new Exception("Precisa ter @");
        }
    }

    public boolean excluirUsuario(Integer idUsuario){
        return this.usuarioRepository.excluirUsuario(idUsuario);
    }
}
