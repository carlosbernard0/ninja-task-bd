package com.jornada.repository;

import com.jornada.entity.Caderno;
import com.jornada.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CadernoRepository {
    public Caderno criarCaderno(Integer idUsuario, Caderno caderno) {
        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            //executar operacao
            String sequence = "select seq_caderno.nextval proxval from DUAL";

            Statement statement = connection.createStatement();

            ResultSet retorno = statement.executeQuery(sequence);
            Integer idCaderno = -1;
            if (retorno.next()) {
                idCaderno = retorno.getInt("proxval");
            }
            String sql = "INSERT INTO CADERNO (ID_CADERNO, NOME_CADERNO, ID_USUARIO) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCaderno);
            preparedStatement.setString(2, caderno.getNomeCaderno());
            preparedStatement.setInt(3, idUsuario);

            caderno.setIdCaderno(idCaderno);
            return caderno;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Caderno> listarCaderno() {
        List<Caderno> listaDeCaderno = new ArrayList<>();

        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM CADERNO";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Caderno caderno = new Caderno();
                caderno.setIdCaderno(resultSet.getInt("id_caderno"));
                caderno.setNomeCaderno(resultSet.getString("nome_caderno"));
//                caderno.setIdUsuario(resultSet.getInt("id_usuario"));
                listaDeCaderno.add(caderno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listaDeCaderno;
        }
    }

    public boolean excluirCaderno(Integer idCaderno){
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from caderno where id_caderno = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCaderno);

            int resultadoExcluir = preparedStatement.executeUpdate();
            return resultadoExcluir > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
