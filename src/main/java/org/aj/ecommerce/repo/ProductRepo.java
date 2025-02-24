package org.aj.ecommerce.repo;

import org.aj.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    /**
     * lower is used for converting to lowercase
     * % is used to search the keyword in between a word
     * concat is used for concatination
     * @param keyWord
     * @return
     */
    @Query("SELECT p from Product p where " +
            "LOWER(p.name) like lower(concat( '%', :keyWord, '%')) OR " +
            "LOWER(p.brand) like lower(concat( '%', :keyWord, '%')) OR " +
            "lower(p.category) like lower(concat('%',:keyWord,'%' )) OR " +
            "lower(p.category) like lower(concat('%',:keyWord,'%'))")
    List<Product> searchProducts(String keyWord);
}