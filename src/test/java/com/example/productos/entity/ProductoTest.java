package com.example.productos.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductoTest {

    @Test
    void constructorVacio_deberiaCrearProductoSinValores() {
        Producto producto = new Producto();

        assertThat(producto.getId()).isNull();
        assertThat(producto.getNombre()).isNull();
        assertThat(producto.getPrecio()).isNull();
        assertThat(producto.getStock()).isNull();
    }

    @Test
    void constructorCompleto_deberiaAsignarTodosLosAtributos() {
        Producto producto = new Producto(1L, "Laptop", 15000.0, 5);

        assertThat(producto.getId()).isEqualTo(1L);
        assertThat(producto.getNombre()).isEqualTo("Laptop");
        assertThat(producto.getPrecio()).isEqualTo(15000.0);
        assertThat(producto.getStock()).isEqualTo(5);
    }

    @Test
    void settersYGetters_deberianActualizarYLeerAtributos() {
        Producto producto = new Producto();

        producto.setId(2L);
        producto.setNombre("Mouse");
        producto.setPrecio(250.0);
        producto.setStock(20);

        assertThat(producto.getId()).isEqualTo(2L);
        assertThat(producto.getNombre()).isEqualTo("Mouse");
        assertThat(producto.getPrecio()).isEqualTo(250.0);
        assertThat(producto.getStock()).isEqualTo(20);
    }
}
