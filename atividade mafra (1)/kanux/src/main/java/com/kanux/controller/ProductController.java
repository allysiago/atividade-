package com.kanux.controller;

import com.kanux.model.Product;
import com.kanux.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD de Produtos
 *
 * Endpoints:
 *   GET    /api/produtos          - Listar todos
 *   GET    /api/produtos/{id}     - Buscar por ID
 *   GET    /api/produtos/buscar?nome=x - Buscar por nome
 *   POST   /api/produtos          - Criar novo
 *   PUT    /api/produtos/{id}     - Atualizar
 *   DELETE /api/produtos/{id}     - Deletar
 */
@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    @Autowired
    private ProductService service;

    // ==================
    // GET - Listar todos
    // ==================
    @GetMapping
    public ResponseEntity<List<Product>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // ==================
    // GET - Buscar por ID
    // ==================
    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ==================
    // GET - Buscar por nome
    // ==================
    @GetMapping("/buscar")
    public ResponseEntity<List<Product>> buscarPorNome(@RequestParam String nome) {
        List<Product> produtos = service.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }

    // ==================
    // POST - Criar produto
    // ==================
    @PostMapping
    public ResponseEntity<Product> criar(@Valid @RequestBody Product product) {
        Product salvo = service.salvar(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // ==================
    // PUT - Atualizar produto
    // ==================
    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody Product product) {
        return service.atualizar(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ==================
    // DELETE - Deletar produto
    // ==================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
