package com.kanux.repository;

import com.kanux.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Busca por nome (contendo o texto, sem case sensitive)
    List<Product> findByNomeContainingIgnoreCase(String nome);
}
