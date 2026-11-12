-- ==========================================
-- PROYECTO: ECOMMERCE API REST
-- BASE DE DATOS: MYSQL
-- ==========================================

CREATE DATABASE IF NOT EXISTS db_ecommerce_leonfernando
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE db_ecommerce_leonfernando;

-- ==========================================
-- TABLA: PRODUCTOS
-- ==========================================

CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(80) NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    marca VARCHAR(80) NOT NULL,
    codigo_barras VARCHAR(50) NOT NULL UNIQUE,
    fecha_ingreso DATE NOT NULL
);