package com.brq.ms03.repositories;

import com.brq.ms03.models.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * O primeiro argumento do JpaRepository é a classe modelo para mapear uma tabela
 * e a classe Java
 *
 * O segundo argumento é o tipo de dado da chave primária
 * */
@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {

    //    SELECT * FROM usuarios u
    //    where u.nome_user = '';
    List<ProfessorModel> findByNome(String nome);

    List<ProfessorModel> findByNomeContains(String nome);

    // JPQL
    // SELECT * FROM usuarios u where u.nome_user like ?
    @Query(value ="SELECT u FROM ProfessorModel u WHERE u.nome like %:nomeBusca%")
    List<ProfessorModel> fetchByNomeLike(@Param("nomeBusca") String nome);


    @Query(value = "SELECT * FROM professor u where u.nome_user like %:nomeBusca%", nativeQuery = true)
    List<ProfessorModel> fetchByNomeLikeNativeQuery(@Param("nomeBusca") String nome);

    List<ProfessorModel> findByNomeContainsAndSalarioContains(String nome, String salario);

    ProfessorModel findFirstByNomeContains(String nome);
}
