package com.jornada.repository;

import com.jornada.entity.Tarefa;
import com.jornada.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    public Tarefa criarTarefa(Integer idCaderno,Tarefa tarefa) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "INSERT INTO TAREFA " +
                    " (ID_TAREFA, NOME_TAREFA, STATUS_TAREFA, ID_CADERNO)" +
                    "VALUES (?,?,?,?)";

            String sqlSequence = "select seq_tarefa.nextval proxval from DUAL";

            Statement statement = connection.createStatement();

            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer idTarefa = -1;
            if (retorno.next()) {
                idTarefa = retorno.getInt("proxval");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.setString(2, tarefa.getNome());
            preparedStatement.setString(3, tarefa.getStatus());
            preparedStatement.setInt(4, idCaderno);

//            int resposta = preparedStatement.executeUpdate();
//            System.out.println("salvarUsuarioDB.resposta: " + resposta);

            tarefa.setIdTarefa(idTarefa);

            return tarefa;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public List<Tarefa> listarTarefas() throws SQLException {
        List<Tarefa> listaDeTarefas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();


            String sql = "select * from tarefa";

            //        Statement statement = connection.createStatement();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setIdTarefa(resultSet.getInt("id_tarefa"));
                tarefa.setNome(resultSet.getString("nome_tarefa"));
                tarefa.setStatus(resultSet.getString("status_tarefa"));
                tarefa.getIdCaderno();


                listaDeTarefas.add(tarefa);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return listaDeTarefas;
        }

    }
}
