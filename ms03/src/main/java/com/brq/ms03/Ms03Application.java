package com.brq.ms03;

import com.brq.ms03.models.ProfessorModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class Ms03Application {

	public static void main(String[] args) {

//		String stringOld = null;
//		if (stringOld == null){
//			System.out.println("Valor da String é nula");
//		}
//		Optional<UsuarioModel> usuarioModel = Optional.empty();
//		Optional<String> stringOptional = Optional.empty();
//		if (stringOptional.isEmpty()){
//			System.out.println("Valor da String é nula");
//		}
//		ArrayList<String> list = null;
//
//		System.out.println(list.get(0));

		SpringApplication.run(Ms03Application.class, args);
	}

}