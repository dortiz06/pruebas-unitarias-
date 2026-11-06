package com.example.productos.controller;

import com.example.productos.entity.Producto;
import com.example.productos.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductoService productoService;

    @Test
    void findAll_deberiaRegresarListaDeProductos() throws Exception {
        Producto laptop = new Producto(1L, "Laptop", 15000.0, 5);
        when(productoService.findAll()).thenReturn(List.of(laptop));

        MvcResult resultado = mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andReturn();

        String cuerpo = resultado.getResponse().getContentAsString();
        assertThat(cuerpo).contains("Laptop");
        assertThat(cuerpo).contains("15000.0");
        verify(productoService).findAll();
    }

    @Test
    void create_deberiaCrearProducto() throws Exception {
        Producto request = new Producto(null, "Teclado", 800.0, 10);
        Producto guardado = new Producto(1L, "Teclado", 800.0, 10);
        when(productoService.create(any(Producto.class))).thenReturn(guardado);

        MvcResult resultado = mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        Producto respuesta = objectMapper.readValue(resultado.getResponse().getContentAsString(), Producto.class);
        assertThat(respuesta.getId()).isEqualTo(1L);
        assertThat(respuesta.getNombre()).isEqualTo("Teclado");
        assertThat(respuesta.getPrecio()).isEqualTo(800.0);
        assertThat(respuesta.getStock()).isEqualTo(10);
        verify(productoService).create(any(Producto.class));
    }
}
