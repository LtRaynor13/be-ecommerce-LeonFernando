package com.ecommerce.LeonFernando.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.LeonFernando.entity.Producto;

@Repository
public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

    // ==========================================
    // NAMED QUERIES
    // ==========================================

    @Query(name = "Producto.buscarPorCategoria")
    List<Producto> buscarPorCategoria(
            @Param("categoria") String categoria);

    @Query(name = "Producto.buscarPorMarca")
    List<Producto> buscarPorMarca(
            @Param("marca") String marca);

    @Query(name = "Producto.buscarPorNombre")
    List<Producto> buscarPorNombre(
            @Param("nombre") String nombre);

    // ==========================================
    // CONSULTAS JPQL PARAMETRIZADAS
    // ==========================================

    @Query("SELECT p FROM Producto p WHERE p.precioVenta >= :precio")
    List<Producto> buscarPorPrecioMinimo(
            @Param("precio") BigDecimal precio);

    @Query("SELECT p FROM Producto p WHERE p.stock > :stock")
    List<Producto> buscarPorStockMayor(
            @Param("stock") Integer stock);

    @Query("SELECT p FROM Producto p WHERE p.precioVenta BETWEEN :minimo AND :maximo")
    List<Producto> buscarPorRangoPrecio(
            @Param("minimo") BigDecimal minimo,
            @Param("maximo") BigDecimal maximo);
}