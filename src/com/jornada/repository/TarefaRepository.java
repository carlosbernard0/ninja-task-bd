package com.jornada.repository;

import com.jornada.entity.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    public Tarefa criarTarefa(Tarefa tarefa) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "INSERT INTO TAREFA (ID_TAREFA, NOME_TAREFA, STATUS_TAREFA, ID_CADERNO)" +
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
            preparedStatement.setInt(4, tarefa.getCaderno().getIdCaderno());

//            int resposta = preparedStatement.executeUpdate();
//            System.out.println("salvarUsuarioDB.resposta: " + resposta);
            preparedStatement.executeUpdate();
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
                tarefa.getCaderno();


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

    public boolean editarTarefa(Tarefa tarefa){
        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            //update
            String sql= "Update Tarefa set nome_tarefa = ?, status_tarefa = ?, id_caderno = ? where id_tarefa = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, tarefa.getIdTarefa());
            preparedStatement.setString(2, tarefa.getNome());
            preparedStatement.setString(3, tarefa.getStatus());
            preparedStatement.setInt(4, tarefa.getCaderno().getIdCaderno());

            //executar
            preparedStatement.executeUpdate();
            System.out.println("-- Atualizando o usuário...");
            return true;

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

    public boolean excluirTarefa(Integer idTarefa){
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from tarefa where id_tarefa = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idTarefa);

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
            System.out.println("erro no banco");
        }

    }

}
