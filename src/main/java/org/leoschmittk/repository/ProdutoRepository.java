
package org.leoschmittk.repository;

import org.leoschmittk.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query(value = "SELECT * FROM PRODUTO u WHERE u.valor = ?1", nativeQuery = true)
    ArrayList<Produto> getProductsByPrice(double value);


    @Query(value = "SELECT * FROM PRODUTO", nativeQuery = true)
    ArrayList<Produto> getAllProducts();
}
