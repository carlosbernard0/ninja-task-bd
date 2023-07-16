package com.jornada.service;

import com.jornada.entity.Caderno;
import com.jornada.repository.CadernoRepository;

public class CadernoService {
    private CadernoRepository cadernoRepository;
    public CadernoService(){
        cadernoRepository = new CadernoRepository();
    }

    public Caderno salvarCaderno(Integer idUsuario, Caderno caderno){
        Caderno cadernoSalvo = cadernoRepository.criarCaderno(idUsuario, caderno);
        System.out.println("Caderno criado com sucesso!Service");
        return cadernoSalvo;
    }
}
