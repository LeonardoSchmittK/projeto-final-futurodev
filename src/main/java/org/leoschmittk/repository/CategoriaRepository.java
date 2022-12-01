package org.leoschmittk.repository;

import org.leoschmittk.model.Categoria;
import org.leoschmittk.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria,Long> {

    @Query(value = "SELECT * FROM CATEGORIA", nativeQuery = true)
    ArrayList<Categoria> getAllCategories();


    @Query(value = "SELECT * FROM CATEGORIA u WHERE u.nome = ?1", nativeQuery = true)
    Categoria getCategoriaPorNome(String nome);
}
