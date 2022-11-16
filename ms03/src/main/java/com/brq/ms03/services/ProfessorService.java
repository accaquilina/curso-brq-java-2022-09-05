package com.brq.ms03.services;

import com.brq.ms03.dtos.ProfessorDTO;
import com.brq.ms03.exceptions.DataCreateException;
import com.brq.ms03.models.ProfessorModel;
import com.brq.ms03.repositories.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * A camada Service é responsável por armazenar as regras de negócio da aplicação
 * */
@Slf4j
@Service
public class ProfessorService implements IProfessorService{

    // ESTE ARRAYLIST É DIDÁTICO, POIS ESTÁ SIMULANDO UM BANCO DE DADOS
//    private ArrayList<UsuarioModel> usuarios = new ArrayList<>();
//    private int counter = 1;

    @Autowired
    private ProfessorRepository usuRepository;

    public void mostrarMensagemService(){
        //System.out.println("Mensagem do serviço");
        log.info("Mensagem do serviço");
    }

    public List<ProfessorDTO> getAllProfessor(){

        // a repository vai executar : SELECT * FROM usuarios;
        List<ProfessorModel> list = usuRepository.findAll();

        // COMO CONVERTER UMA LISTA DE MODEL PARA LISTA DE DTO?

        List<ProfessorDTO> listDTO = new ArrayList<>();

        // Tipo da variável -
        for (ProfessorModel balde : list) {
            listDTO.add( balde.toDTO() );
//            UsuarioDTO dto = UsuarioDTO
//                    .builder()
//                    .id(balde.getId())
//                    .email(balde.getEmail())
//                    .nome(balde.getNome())
//                    .endereco( balde.getEndereco() == null ? null : balde.getEndereco().toDTO())
//                    .consorcios( balde.getConsorcios().isEmpty() ? null : balde.getConsorcios().stream().map( x -> x.toDTO() ).collect(Collectors.toList()))
//                    .build();
//
//            listDTO.add( dto );
        }

        return listDTO;
        //return usuarios;
    }

    public List<ProfessorDTO> getAllProfessor2(){

        List<ProfessorModel> list = usuRepository.findAll();

        List<ProfessorDTO> listDTO = new ArrayList<>();

        for (ProfessorModel balde : list) {
            balde.setId( 2 * balde.getId() );
            balde.setNome( balde.getNome() + "JAVA" );
            listDTO.add( balde.toDTO() );
        }
        return listDTO;
    }

    public ProfessorDTO create(ProfessorDTO professor){

        //usuarios.add(usuario);
        //counter++;

        // TEMOS QUE CONVERTER UM DTO PARA UM MODEL
        // jeito Fabrizio (Burro)
//        usuarioDTOtoModel.setId( usuario.getId() );
//        usuarioDTOtoModel.setNome(usuario.getNome());
//        usuarioDTOtoModel.setTelefone(usuario.getTelefone());
//        usuarioDTOtoModel.setEmail(usuario.getEmail());

        ProfessorModel professorSalvo = null;

        try{
            // INSERT INTO usuarios (name_user, email_user ) VALUEs()....
            professorSalvo = usuRepository.save( professor.toModel() );
            // return "POST Usuários";
            //return usuario;
            log.info(professorSalvo.toString());

            //caminho feliz
            return professorSalvo.toDTO();
        }
        catch (Exception exception){
            log.error("Erro ao salvar o usuário: " + exception.getMessage());
            //throw new RuntimeException("Erro ao salvar no banco de dados");
            throw new DataCreateException("Erro ao salvar usuário");
        }
        /* O famoso NULLPOIIIINTERRRREXCEPPTIIIONN (NullPointException)
         * quando tentamos executar um método de uma variável nula,
         * acontece a exceção NullPointException
         * */

    }

    public ProfessorDTO update(int id, ProfessorDTO professorBody)  {

        ProfessorModel professor = usuRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Professor não localizado") );


        professor.setSalario( professorBody.getSalario() );
        professor.setNome( professorBody.getNome() );
        professor.setTelefone( professorBody.getTelefone() );

        return usuRepository.save(professor).toDTO();


//        // ver se os dados existem
//
//        // eu achei o usuário no banco de dados
//        if (usuarioOptional.isPresent()){
//            // retorna os valores do usuário encontrado no banco de dados
//            UsuarioModel meuUsuario = usuarioOptional.get();
//
//            meuUsuario.setEmail( usuarioBody.getEmail() );
//            meuUsuario.setNome( usuarioBody.getNome() );
//
//            UsuarioModel usuarioSalvo = usuRepository.save(meuUsuario);
//
//            return usuarioSalvo;
//        }
//        // não achei o usuário no banco
//        else{
//            return usuarioOptional.orElseThrow( () -> new RuntimeException("Usuário não encontrado"));
//        }

//        // como achar o usuário a ser alterado?
//            if (usuarios.get(i).getId() == id){
//                // achamos o usuário a ser alterado
//                usuarios.get(i).setNome( usuarioBody.getNome() );
//                usuarios.get(i).setEmail( usuarioBody.getEmail() );
//
//                return usuarios.get(i);
//            } // if
//        }// for
//
//        return null;
    }

    public String delete(int id){
        // FORECH
//            usuarios.remove(usuarioLocal);
//        }
//        for (int i = 0; i < usuarios.size(); i++){
//            // se achar o usuário, então delete do arraylist
//            if (usuarios.get(i).getId() == id){
//                usuarios.remove(i);
//                return "Usuário delatado com sucesso!";
//            } // if
//        } // for
//        return "Usuário não encontrado";

        final var professor = usuRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Professor não localizado") );

        //log.info("deletando usuário id: " + usuario.getId() + " com sucesso, email " + usuario.getEmail()  );

        log.info("deletando professor id: {} com sucesso, salario : {}",
                professor.getId(), professor.getSalario() );

        usuRepository.deleteById(id);
        return "Professor delatado com sucesso!";
    }

    public ProfessorDTO getOne(int id){

        ProfessorModel professor = usuRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Professor não localizado"));

        return professor.toDTO();

    }

    public List<ProfessorDTO> fetchProfessorByNome(String nomeBusca){

        // pesquisa pelo findBy
        //List<UsuarioModel> list = usuRepository.findByNome(nomeBusca);
        //List<UsuarioModel> list = usuRepository.findByNomeContains(nomeBusca);

        // usando JPQL
        // List<UsuarioModel> list = usuRepository.fetchByNomeLike(nomeBusca);

        // usando Native Query
        List<ProfessorModel> list = usuRepository.fetchByNomeLikeNativeQuery(nomeBusca);


        List<ProfessorDTO> listDTO = new ArrayList<>();

        // Tipo da variável -
        for (ProfessorModel balde : list) {
            listDTO.add( balde.toDTO() );
        }

        return listDTO;
    }

    public List<ProfessorDTO> fetchProfessorByNomeAndSalario(String nomeBusca, String salarioBusca){

        //List<UsuarioModel> list = usuRepository.findByNome(nomeBusca);
        List<ProfessorModel> list = usuRepository.findByNomeContainsAndSalarioContains(nomeBusca, salarioBusca);


        List<ProfessorDTO> listDTO = new ArrayList<>();

        // Tipo da variável -
        for (ProfessorModel balde : list) {
            listDTO.add( balde.toDTO() );
        }

        return listDTO;
    }


}