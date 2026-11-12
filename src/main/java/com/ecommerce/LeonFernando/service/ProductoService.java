package com.ecommerce.LeonFernando.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.LeonFernando.dto.ProductoRequestDTO;
import com.ecommerce.LeonFernando.dto.ProductoResponseDTO;
import com.ecommerce.LeonFernando.entity.Producto;
import com.ecommerce.LeonFernando.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // LISTAR TODOS LOS PRODUCTOS
    public List<ProductoResponseDTO> listarProductos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    // BUSCAR PRODUCTO POR ID
    public ProductoResponseDTO buscarPorId(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        return convertirAResponseDTO(producto);
    }

    // REGISTRAR PRODUCTO
    public ProductoResponseDTO registrarProducto(ProductoRequestDTO dto) {

        Producto producto = convertirAEntidad(dto);

        Producto guardado = productoRepository.save(producto);

        return convertirAResponseDTO(guardado);
    }

    // ACTUALIZAR PRODUCTO
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setMarca(dto.getMarca());
        producto.setCodigoBarras(dto.getCodigoBarras());
        producto.setFechaIngreso(dto.getFechaIngreso());

        Producto actualizado = productoRepository.save(producto);

        return convertirAResponseDTO(actualizado);
    }

    // ELIMINAR PRODUCTO
    public void eliminarProducto(Long id) {

        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Producto no encontrado"
            );
        }

        productoRepository.deleteById(id);
    }

    // CONVERTIR DTO A ENTIDAD
    private Producto convertirAEntidad(ProductoRequestDTO dto) {

        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setMarca(dto.getMarca());
        producto.setCodigoBarras(dto.getCodigoBarras());
        producto.setFechaIngreso(dto.getFechaIngreso());

        return producto;
    }

    // CONVERTIR ENTIDAD A DTO
    private ProductoResponseDTO convertirAResponseDTO(Producto producto) {

        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getPrecioCompra(),
                producto.getPrecioVenta(),
                producto.getStock(),
                producto.getMarca(),
                producto.getCodigoBarras(),
                producto.getFechaIngreso()
        );
    }
}