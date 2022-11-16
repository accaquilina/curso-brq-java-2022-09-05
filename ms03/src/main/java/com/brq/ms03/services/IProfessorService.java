package com.brq.ms03.services;

import com.brq.ms03.dtos.ProfessorDTO;

import java.util.List;

public interface IProfessorService {

    void mostrarMensagemService();

    List<ProfessorDTO> getAllProfessor();

    List<ProfessorDTO> getAllProfessor2();

    ProfessorDTO create(ProfessorDTO professor);

    ProfessorDTO update(int id, ProfessorDTO professorBody);

    String delete(int id);

    ProfessorDTO getOne(int id);

    List<ProfessorDTO> fetchUsuariosByNome(String nomeBusca);

    List<ProfessorDTO> fetchUsuariosByNomeAndSalario(String nomeBusca, String salarioBusca);
}