package com.jornada.repository;

import com.jornada.entity.Usuario;

import java.sql.*;

public class UsuarioRepository {
    public Usuario salvarUsuarioDB(Usuario  usuario){
    //abrir conexao,
        try {
            Connection connection = ConexaoDB.getConnection();

            String sql = "INSERT INTO CLIENTE " +
                    " (ID_USUARIO, NOME_USUARIO, EMAIL_USUARIO,SENHA_USUARIO, DATA_REGISTRO" +
                    "VALUES"+
                    "(?,?,?,?,?)";

            String sqlSequence = "select seq_usario.nextval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);
            
            Integer proximoId;
            if(retorno.next()){
                proximoId
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,proximoId);
            preparedStatement.setString(2, usuario.getNome_usuario());
            preparedStatement.setString(3, usuario.getEmail_usuario());
            preparedStatement.setString(4, usuario.getSenha_usuario());
            preparedStatement.setDate(5,new Date(usuario.getData_registro().getTime()));

            int resposta = preparedStatement.executeUpdate();
            System.out.println("salvarUsuarioDB.resposta" + resposta);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // executar operacao, fechar conexao...
    }
}
