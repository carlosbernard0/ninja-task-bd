package com.jornada.service;

import com.jornada.entity.Caderno;
import com.jornada.entity.Usuario;
import com.jornada.repository.CadernoRepository;

import java.util.List;

public class CadernoService {
    private CadernoRepository cadernoRepository;
    public CadernoService(){
        cadernoRepository = new CadernoRepository();
    }

    public Caderno salvarCaderno(Caderno caderno){
        Caderno cadernoSalvo = cadernoRepository.criarCaderno(caderno);
        return cadernoSalvo;
    }

    public List<Caderno> listar(){
        List<Caderno> lista = cadernoRepository.listarCaderno();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    public boolean excluirCaderno(Integer idCaderno){
        return this.cadernoRepository.excluirCaderno(idCaderno);
    }
}
