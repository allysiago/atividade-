package com.kanux.service;

import com.kanux.model.Product;
import com.kanux.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // ==================
    // CREATE
    // ==================
    public Product salvar(Product product) {
        return repository.save(product);
    }

    // ==================
    // READ - Listar todos
    // ==================
    public List<Product> listarTodos() {
        return repository.findAll();
    }

    // ==================
    // READ - Buscar por ID
    // ==================
    public Optional<Product> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // ==================
    // READ - Buscar por nome
    // ==================
    public List<Product> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    // ==================
    // UPDATE
    // ==================
    public Optional<Product> atualizar(Long id, Product dadosNovos) {
        return repository.findById(id).map(produto -> {
            produto.setNome(dadosNovos.getNome());
            produto.setDescricao(dadosNovos.getDescricao());
            produto.setPreco(dadosNovos.getPreco());
            produto.setQuantidade(dadosNovos.getQuantidade());
            return repository.save(produto);
        });
    }

    // ==================
    // DELETE
    // ==================
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
