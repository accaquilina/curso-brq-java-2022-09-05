package com.brq.ms05.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {

    @Id
    private String id;
    private String nome;
    private String email;


}
