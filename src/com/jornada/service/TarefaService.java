package com.jornada.service;

import com.jornada.entity.Caderno;
import com.jornada.entity.Tarefa;
import com.jornada.entity.Usuario;
import com.jornada.repository.CadernoRepository;
import com.jornada.repository.TarefaRepository;

import java.sql.SQLException;
import java.util.List;

public class TarefaService {
    private TarefaRepository tarefaRepository;
    public TarefaService(){
        tarefaRepository = new TarefaRepository();
    }

    public Tarefa salvarTarefa(Integer idCaderno, Tarefa tarefa){
        Tarefa tarefaSalva = tarefaRepository.criarTarefa(idCaderno, tarefa);
        return tarefa;
    }

    public List<Tarefa> listarTarefas()throws SQLException {
        List<Tarefa> lista = tarefaRepository.listarTarefas();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    public boolean editarTarefa(Tarefa tarefa) throws Exception {
        return tarefaRepository.editarTarefa(tarefa);
    }

    public boolean excluirTarefa(Integer idTarefa){
        return this.tarefaRepository.excluirTarefa(idTarefa);
    }
}
