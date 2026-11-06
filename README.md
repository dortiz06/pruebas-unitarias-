# gestion-productos

Proyecto de práctica de laboratorio: capa de servicios con **JUnit 5**, **Mockito** y **AssertJ**.

## Requisitos

- Java 17
- Maven 3.9+

Si `mvn -version` muestra otra versión de Java:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

## Compilar

```bash
mvn clean compile
```

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API queda disponible en:

- `GET  /api/productos` — listar productos
- `POST /api/productos` — crear producto
- Consola H2: `http://localhost:8080/h2-console`

## Ejecutar las pruebas unitarias

```bash
mvn test
```

Resultado esperado:

```
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Las pruebas de `ProductoService` usan mocks (`@Mock` / `@InjectMocks`) y **no** levantan Spring ni una base de datos real.
