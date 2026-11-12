package com.ecommerce.LeonFernando.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.LeonFernando.dto.ProductoRequestDTO;
import com.ecommerce.LeonFernando.dto.ProductoResponseDTO;
import com.ecommerce.LeonFernando.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // LISTAR TODOS LOS PRODUCTOS
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos() {

        return ResponseEntity.ok(productoService.listarProductos());
    }

    // BUSCAR PRODUCTO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    // REGISTRAR PRODUCTO
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> registrarProducto(
            @Valid @RequestBody ProductoRequestDTO dto) {

        ProductoResponseDTO producto = productoService.registrarProducto(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    // ACTUALIZAR PRODUCTO
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {

        return ResponseEntity.ok(
                productoService.actualizarProducto(id, dto)
        );
    }

    // ELIMINAR PRODUCTO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}