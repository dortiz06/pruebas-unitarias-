package com.example.productos.service;

import com.example.productos.entity.Producto;
import com.example.productos.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void findAll_deberiaRegresarListaDeProductos() {
        Producto laptop = new Producto(1L, "Laptop", 15000.0, 5);
        Producto mouse = new Producto(2L, "Mouse", 250.0, 20);
        when(productoRepository.findAll()).thenReturn(List.of(laptop, mouse));

        List<Producto> resultado = productoService.findAll();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Laptop");
        assertThat(resultado.get(0).getPrecio()).isEqualTo(15000.0);
        assertThat(resultado.get(0).getStock()).isEqualTo(5);
        verify(productoRepository).findAll();
    }

    @Test
    void create_deberiaGuardarYRegresarProducto() {
        Producto nuevo = new Producto(null, "Teclado", 800.0, 10);
        Producto productoGuardado = new Producto(1L, "Teclado", 800.0, 10);
        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = productoService.create(nuevo);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Teclado");
        assertThat(resultado.getPrecio()).isEqualTo(800.0);
        assertThat(resultado.getStock()).isEqualTo(10);
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void create_deberiaLanzarExcepcionCuandoElNombreEsNulo() {
        Producto invalido = new Producto(null, null, 100.0, 1);

        assertThatThrownBy(() -> productoService.create(invalido))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("nombre");
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void create_deberiaLanzarExcepcionCuandoElNombreEstaVacio() {
        Producto invalido = new Producto(null, "   ", 100.0, 1);

        assertThatThrownBy(() -> productoService.create(invalido))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("nombre");
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void create_deberiaLanzarExcepcionCuandoElPrecioEsCero() {
        Producto invalido = new Producto(null, "Monitor", 0.0, 3);

        assertThatThrownBy(() -> productoService.create(invalido))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("precio");
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void create_deberiaLanzarExcepcionCuandoElPrecioEsNegativo() {
        Producto invalido = new Producto(null, "Monitor", -100.0, 3);

        assertThatThrownBy(() -> productoService.create(invalido))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("precio");
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void create_deberiaLanzarExcepcionCuandoElPrecioEsNulo() {
        Producto invalido = new Producto(null, "Monitor", null, 3);

        assertThatThrownBy(() -> productoService.create(invalido))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("precio");
        verify(productoRepository, never()).save(any(Producto.class));
    }
}
