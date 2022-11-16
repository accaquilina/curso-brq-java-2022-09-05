package com.brq.ms03.controllers;

import com.brq.ms03.dtos.ProfessorDTO;
import com.brq.ms03.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brq.ms03.services.IProfessorService;

import javax.validation.Valid;
import java.util.List;


// comentário

/*
 * comentário
 * */

@RestController
public class ProfessorController {

    // private UsuarioService usuService = new UsuarioService();
    // @Autowired é importante pois permite que o Spring "instancie" o objeto do tipo UsuarioService
    @Autowired
//    private UsuarioService usuService;
    private IProfessorService usuService;

    /*
     * o @GetMapping permite associoar o verbo GET com a rota /usuarios
     * */
    @GetMapping("professor")
    public ResponseEntity<List<ProfessorDTO>> getAllUsuarios(){

        // usuService.mostrarMensagemService();

        var usuarios = usuService.getAllProfessor();

        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping("professor")
    public ResponseEntity<ProfessorDTO> create(@Valid @RequestBody ProfessorDTO professor){

        var professorResponse = usuService.create(professor);

        return ResponseEntity.ok().body(professorResponse);

    } // create

    // /usuarios/1 -> o valor do id vai ser 1

    @PatchMapping("professor/{id}")
    public ResponseEntity<ProfessorDTO> update(@RequestBody ProfessorDTO professorBody,
                                             @PathVariable int id ){

        var tiberio = usuService.update(id, professorBody);
        return ResponseEntity.ok().body(tiberio);
    } // update()

    @DeleteMapping("professor/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){

        var resp = usuService.delete(id);
        return ResponseEntity.ok().body(resp);
    } // delete

    // busca por apenas um usuário (pelo id)
    @GetMapping("professor/{id}")
    public ResponseEntity<ProfessorDTO> getOne(@PathVariable int id){

        var u = usuService.getOne(id);
        return ResponseEntity.ok().body(u);

    } // getOne

    // usuarios/nome/Fabrizio
    @GetMapping("professor/nome/{nomeBusca}")
    public ResponseEntity<List<ProfessorDTO>> fetchProfessorByNome(@PathVariable String nomeBusca){
        // TODO: Não esquecer do ResponseEntity
        // TODO: fazer a busca usando o operador like
        var list = usuService.fetchProfessorByNome(nomeBusca);
        return ResponseEntity.ok().body(list);

        // return ResponseEntity.ok().body(usuService.fetchUsuariosByNome(nomeBusca));
    }

    // usuarios/nome/Fabrizio
    @GetMapping("usuarios/nome/{nomeBusca}/email/{emailBusca}")
    public ResponseEntity<List<ProfessorDTO>> fetchUsuariosByNomeAndEmail(
            @PathVariable String nomeBusca,
            @PathVariable String emailBusca){
        // TODO: Não esquecer do ResponseEntity
        // TODO: fazer a busca usando o operador like

        // var nome = "Fabrizio";

        // List<UsuarioDTO> list = usuService.fetchUsuariosByNomeAndEmail(nomeBusca, emailBusca);
        var list = usuService.fetchProfessorByNomeAndSalario(nomeBusca, salarioBusca);

        return ResponseEntity.ok().body(list);
    }
package com.brq.ms03.exceptions;

    public class DataCreateException extends RuntimeException{

        public DataCreateException(String mensagem){
            super(mensagem);
        }

        public DataCreateException(String mensagem, Throwable causa){
            super(mensagem,causa);
        }
    }

}