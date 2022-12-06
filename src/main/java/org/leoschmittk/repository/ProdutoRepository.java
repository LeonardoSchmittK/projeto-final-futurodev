
package org.leoschmittk.repository;

import org.leoschmittk.model.Produto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query(value = "SELECT * FROM PRODUTO u WHERE u.valor = ?1", nativeQuery = true)
    ArrayList<Produto> getProductsByPrice(double value);


    @Query(value = "SELECT * FROM PRODUTO", nativeQuery = true)
    ArrayList<Produto> getAllProducts();


    @Modifying
    @Query("delete from Produto t where t.id = ?1")
    void delete(Long entityId);


}
