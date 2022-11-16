package com.brq.ms03.models;

import com.brq.ms03.dtos.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.List;

/*
 * @Data, que faz o papel dos Getters, Setters e toString()
 * @Entity "diz" que a classe UsuarioModel vai ser mapeada com uma tabela no banco de dados
 * @Table especifica o nome da tabela que esta classe vai mapear
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professor")
public class ProfessorModel {

    // UUID -> é um conjunto de letras e números para identificar unicamente um registro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "salario")
    private String salario;

    @Column(name = "telefone")
    private String telefone;

    // no mappedBy guardamos a variável JAVA que mapeia esta entidade (UsuarioModel)
    @OneToMany(mappedBy = "professor")


    public ProfessorDTO toDTO(){
        ModelMapper mapper = new ModelMapper();

        return mapper.map(this, ProfessorDTO.class);
    }
}