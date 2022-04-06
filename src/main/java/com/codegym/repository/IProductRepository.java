package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Query(value = "select * from " +
            "products where (price between ?1 and ?2) " +
            "and image is not null", nativeQuery = true)
    Iterable<Product> findProductPriceBetween(Double min, Double max);

    @Query(value = "select * from products where category_id = ?1", nativeQuery = true)
    Page<Product> findProductByCategoryId(Long id, Pageable pageable);
}
